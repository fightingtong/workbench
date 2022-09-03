package com.vv.work.user.controller;

import com.vv.work.util.RespResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import util.IKAnalyzerUtil;
import util.Pinyin4jUtil;
import util.SpellTool;

import java.io.IOException;
import java.util.*;

/**
 * @description:
 * @author: tonghp
 * @date: 2022/4/20 12:28
 **/
@RestController
@RequestMapping("/test"+ "${xx.yy:123}")
public class TestController {

    @Value("xxx_" + "${xx.yy:123}")
    private String testStr;


    @GetMapping("/ik")
    public RespResult<String> ik() throws IOException {
        String text = "童海鹏说：中华人民共和国成立九十周年了！";
        List<String> list = IKAnalyzerUtil.cut(text);
        System.out.println(list);
        return RespResult.ok();
    }

    @GetMapping("/pinyin")
    public RespResult<String> pinyin(){
        String str = "测试";
        String pinyin = Pinyin4jUtil.converterToSpell(str);
        System.out.println(str + " pin yin ：" + pinyin);

        pinyin = Pinyin4jUtil.converterToFirstSpell(str);
        System.out.println(str + " short pin yin ：" + pinyin);
        return RespResult.ok();
    }

    @GetMapping("/spell")
    public RespResult<String> spell(){
        //long start = System.currentTimeMillis();
        String spell = "woaibeijintiananmen";
        spell = "tonghpeng";
        String result = SpellTool.trimSpell(spell);
        //long end = System.currentTimeMillis();
        //System.out.println("用时:" + (end - start) + "毫秒");
        System.out.println(result);
        return RespResult.ok();
    }



    /**
     * test xx
     * @return
     */
    @GetMapping("/xx001")
    public String xxJwt(){
        String xx = testStr;
        return xx;
    }

    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    private DefaultRedisScript<Long> loadScanDelKeyRedisScript;

    @GetMapping("/test/clear")
    public String testClear(String part) {
        part = StringUtils.isEmpty(part) ? "part" : part;
        Map keyMap = new HashMap();
        String keyPrefix = "vv-ucc:global:test:" + part + ":";
        for (int i = 0; i < 10000; i++) {
            String key = keyPrefix + i;
            System.out.println(key);
            keyMap.put(key, i);
        }
        redisTemplate.opsForValue().multiSet(keyMap);
        List<String> keys1 = scanPatternKeysExecute(keyPrefix + "*", 2000);
        if (!CollectionUtils.isEmpty(keys1)) {
            redisTemplate.delete(keys1);
        }
        return "ok";
    }

    /**
     * lua清理redis（不适用与分片集群）
     * @param part
     * @return
     */
    @GetMapping("/test/lua/clear")
    public String testLuaClear(String part) {
        part = StringUtils.isEmpty(part) ? "part" : part;
        Map keyMap = new HashMap();
        String keyPrefix = "vv-ucc:global:test:" + part + ":";
        for (int i = 0; i < 10000; i++) {
            String key = keyPrefix + i;
            System.out.println(key);
            keyMap.put(key, i);
        }
        redisTemplate.opsForValue().multiSet(keyMap);
        redisTemplate.execute(loadScanDelKeyRedisScript, Collections.singletonList(keyPrefix + "*"), 2000);
        return "ok";
    }

    /**
     * 遍历通配keys
     *
     * @param patternKey
     * @param count
     * @return
     */
    public List<String> scanPatternKeysExecute(String patternKey, long count) {
        // scan操作
        List<String> keys = new ArrayList<>();
        redisTemplate.execute((RedisCallback) conn -> {
            ScanOptions options = ScanOptions.scanOptions().match(patternKey).count(count).build();
            Cursor<byte[]> cursor = conn.scan(options);
            while (cursor.hasNext()) {
                byte[] keyAtr = cursor.next();
                keys.add(new String(keyAtr));
            }
            return null;
        });
        return keys;
    }
}
