local key = KEYS[1]
local nowMill = tonumber(ARGV[1]) --当前时间戳毫秒
local limit = tonumber(ARGV[2]) --限流次数
local interval = tonumber(ARGV[3]) --限流间隔 单位是毫秒
local startIndex = 0
local endIndex = limit -1

--1、判断是否存在key
local isExists = redis.call("EXISTS",key);
if not isExists then
    redis.call("LPUSH",key,nowMill) --不存在 插入
else
    local size = tonumber(redis.call("LLEN",key)) --获取列表长度
    if size >= limit then
        local lastMill = tonumber(redis.call("RPOP",key)) --获取最后一个数据
        local diffMill = nowMill - lastMill
        if diffMill <= interval then
            redis.call("LPUSH",key,nowMill) --从头部插入数据
            redis.call("LTRIM",key,startIndex,endIndex) --剪切保留最新的limit条记录
            return false
        end
    end
    redis.call("LPUSH",key,nowMill) --从头部插入数据
end
return true