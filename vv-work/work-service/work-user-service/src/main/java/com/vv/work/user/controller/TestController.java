package com.vv.work.user.controller;

import com.vv.work.jwt.service.JWTTokenService;
import com.vv.work.user.model.UserInfo;
import com.vv.work.util.RespResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import util.IKAnalyzerUtil;
import util.Pinyin4jUtil;
import util.SpellTool;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

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

}
