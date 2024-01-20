package com.vv.work.search.feign;

import com.vv.work.search.model.UserInfoEs;
import com.vv.work.util.RespResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * @description:
 * @author: tonghp
 * @date: 2022/4/18 23:58
 **/
@FeignClient(value = "work-search")
@RequestMapping("/user/info/search")
public interface UserInfoSearchFeign {

    /****
     * 单个商品导入
     */
    @PostMapping(value = "/add")
    public RespResult add(@RequestBody UserInfoEs userInfoEs);

    /***
     * 根据ID删除
     * @param id
     * @return
     */
    @DeleteMapping(value = "/del/{id}")
    public RespResult del(@PathVariable("id")String id);
}
