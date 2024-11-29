-- 令牌桶在redis中的key值
local key = KEYS[1]
-- 令牌单位时间填充速率
local rate = tonumber(ARGV[1])
-- 令牌桶容量
local capacity = tonumber(ARGV[2])
-- 当前时间
local current_time = tonumber(ARGV[3])
-- 请求需要的令牌数
local requested = tonumber(ARGV[4])
-- 令牌过期时间
local tokenExpireTime = tonumber(ARGV[5])

-- 桶中的令牌数量
local tokens_count_field = 'bucket_token_count'
-- 上次更新时间
local last_refreshed_field = 'last_update_time'

-- 令牌桶容量/令牌填充速率=令牌桶填满所需的时间
local fill_time = capacity / rate
-- 令牌过期时间
local ttl = math.max(math.floor(fill_time * 2), tokenExpireTime)

-- 获取上一次令牌桶剩余的令牌数
local last_tokens = tonumber(redis.call('hget', key, tokens_count_field))
-- 如果没有获取到，可能是令牌桶是新的，之前不存在该令牌桶，或者该令牌桶已经好久没有使用
-- 过期了，这里需要对令牌桶进行初始化，初始情况，令牌桶是满的
if last_tokens == nil then
    last_tokens = capacity
end
-- 获取上一次刷新的时间，如果没有，或者已经过期，那么初始化为0
local last_refreshed = tonumber(redis.call('hget', key, last_refreshed_field))
if last_refreshed == nil then
    last_refreshed = 0
end

-- 计算上一次刷新时间和本次刷新时间的时间差(毫秒)
local delta = math.max(0, current_time - last_refreshed)
-- delta*rate = 这个时间差可以填充的令牌数，
-- 令牌桶中先存在的令牌数 = 填充令牌数+令牌桶中原有的令牌数
-- 以为令牌桶有容量，所以如果计算的值大于令牌桶容量，那么以令牌容容量为准
local filled_tokens = math.min(capacity, last_tokens + (delta / 1000 * rate))
-- 判断令牌桶中的令牌数是都满本次请求需要的令牌数，如果不满足，说明被限流了
local allowed = filled_tokens >= requested
-- 剩余令牌数
local remain_tokens = filled_tokens
-- 是否被限流，0代表限流，1代表没有限流
local allowed_num = 0
if allowed then
    remain_tokens = filled_tokens - requested
    allowed_num = 1
end
-- 存储本次操作后，令牌桶中的令牌数以及本次刷新时间
redis.call('hset', key, tokens_count_field, remain_tokens)
redis.call('hset', key, last_refreshed_field, current_time)
redis.call('expire', key, ttl)
-- 返回是否被限流标志以及令牌桶剩余令牌数
return { allowed_num, remain_tokens }