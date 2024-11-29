-- 限流资源
local key = KEYS[1]
-- 限流请求数
local limitCount = ARGV[1]
-- 限流时间
local limitTime = ARGV[2]
-- 当前请求数
local currentCount = redis.call('get', key)
if (currentCount and tonumber(currentCount) >= tonumber(limitCount)) then
    return -1
end
currentCount = redis.call('incr', key)
if (tonumber(currentCount) == 1) then
    redis.call('expire', key, limitTime)
end
return tonumber(limitCount) - tonumber(currentCount)