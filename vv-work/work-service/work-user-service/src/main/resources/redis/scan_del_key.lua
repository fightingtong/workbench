local limitSize = tonumber(ARGV[1]) -- 最多删除多少个key
local batchSize = limitSize -- scan一次最多扫描多少个key

if (batchSize > 10000) then -- 一次扫描不能超过1w条
    batchSize = 10000
end

local function scan(key)
    local cursor = 0
    local keynum = 0

    repeat
        local res = redis.call("scan", cursor, "match", key, 'COUNT', batchSize)

        if (res ~= nil and #res >= 0) then
            redis.replicate_commands()
            cursor = tonumber(res[1])
            local ks = res[2]
            local size = #ks
            for i=1,size,1 do
                redis.call("del", tostring(ks[i]))
                keynum = keynum + 1
            end
        end
    until (cursor <= 0)

    return keynum
end

local a = #KEYS
local b = 1
local total = 0
while (b <= a)
do
    total = total + scan(KEYS[b])
    b = b + 1
end

return total