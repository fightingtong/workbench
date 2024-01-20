package com.vv.work.user.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.vv.work.user.model.UserInfo;
import com.vv.work.user.service.UserInfoService;
import com.vv.work.util.RespResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @description:
 * @author: tonghp
 * @date: 2022/4/16 20:30
 **/
@RestController
@RequestMapping("/user/info")
public class UserInfoController {
    @Autowired
    UserInfoService userInfoService;

    /***
     * 增加用户信息
     */
    @PostMapping
    public RespResult add(@RequestBody UserInfo brand){
        userInfoService.save(brand);
        return RespResult.ok();
    }

    /****
     * 修改用户信息
     */
    @PutMapping
    public RespResult update(@RequestBody UserInfo brand){
        userInfoService.updateById(brand);
        return RespResult.ok();
    }

    /****
     * 删除用户信
     */
    @DeleteMapping("/{id}")
    public RespResult delete(@PathVariable(value = "id") Integer id){
        userInfoService.removeById(id);
        return RespResult.ok();
    }

    @GetMapping("/{id}")
    public RespResult<UserInfo> get(@PathVariable("id") Integer id){
        UserInfo userInfo = userInfoService.getById(id);
        return RespResult.ok(userInfo);
    }

    /****
     * 条件查询
     */
    @PostMapping(value = "/list")
    public RespResult<List<UserInfo>> list(@RequestBody(required = false) UserInfo brand){
        // 查询
        List<UserInfo> list = userInfoService.queryList(brand);
        return RespResult.ok(list);
    }

    /****
     * 条件分页查询
     */
    @PostMapping(value = "/list/{page}/{size}")
    public RespResult<Page<UserInfo>> list(
            @PathVariable(value = "page")Long currentPage,
            @PathVariable(value = "size")Long size,
            @RequestBody(required = false) UserInfo brand){
        // 分页查询
        Page<UserInfo> userInfoPage = userInfoService.queryPageList(currentPage,size,brand);
        return RespResult.ok(userInfoPage);
    }
}
