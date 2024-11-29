-- 限流关键字
local key = KEYS[1]
-- 当前时间戳的key
local current_thread = ARGV[1]
-- 滑动窗口大小
local window_size = tonumber(ARGV[2])
-- 限制的请求数
local limit = tonumber(ARGV[3])
-- 当前时间戳
local current_time = tonumber(ARGV[4])
-- 已经用掉的请求数
local last_requested = 0

local exists_key = redis.call('exists', key)
if (exists_key == 1) then
    last_requested = redis.call('zcard', key)
end
-- 本次允许通过的请求数
local allowed_num = 0
-- 可用的请求数量
local remain_request = limit - last_requested
-- 已使用的请求数量 未超过限制数量
if (last_requested < limit) then
    allowed_num = 1
    -- 将当前线程放入集合中
    redis.call('zadd', key, current_time, current_thread)
end
-- 窗口移动，移除前一个窗口时间剩余的线程
redis.call('zremrangebyscore', key, 0, current_time - window_size * 1000)
redis.call('expire', key, window_size)
return { allowed_num, remain_request }