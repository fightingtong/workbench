package com.vv.work.search.controller;

import com.vv.work.search.model.UserInfoEs;
import com.vv.work.search.service.UserInfoSearchService;
import com.vv.work.util.RespResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @description:
 * @author: tonghp
 * @date: 2022/4/19 0:04
 **/
@RestController
@RequestMapping("/user/info/search")
public class UserInfoSearchController {
    @Autowired
    private UserInfoSearchService userInfoSearchService;

    /****
     * 单个商品导入
     */
    @PostMapping(value = "/add")
    public RespResult add(@RequestBody UserInfoEs userInfoEs){
        userInfoSearchService.add(userInfoEs);
        return RespResult.ok();
    }

    /***
     * 根据ID删除
     * @param id
     * @return
     */
    @DeleteMapping(value = "/del/{id}")
    public RespResult del(@PathVariable("id")String id){
        userInfoSearchService.del(id);
        return RespResult.ok();
    }

    /****
     * 商品搜索
     */
    @GetMapping
    public RespResult<Map<String,Object>> search(@RequestParam Map<String,Object> searchMap){
        Map<String,Object> result = userInfoSearchService.search(searchMap);
        return RespResult.ok(result);
    }
}
