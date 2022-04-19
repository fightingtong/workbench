package com.vv.work.canal.listener;

import com.alibaba.fastjson.JSON;
import com.vv.work.search.feign.UserInfoSearchFeign;
import com.vv.work.search.model.UserInfoEs;
import com.vv.work.user.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.javatool.canal.client.annotation.CanalTable;
import top.javatool.canal.client.handler.EntryHandler;

/**
 * @description:
 * @author: tonghp
 * @date: 2022/4/18 16:37
 **/
@Component
@CanalTable("user_info")
public class UserInfoHandler implements EntryHandler<UserInfo> {

    @Autowired
    UserInfoSearchFeign userInfoSearchFeign;

    @Override
    public void insert(UserInfo userInfo) {
        System.out.println("insert---" + userInfo);
        if(userInfo.getStatus().intValue() != 0){
            // 将UserInfo转成JSON，再将JSON转成UserInfoEs
            userInfoSearchFeign.add(JSON.parseObject(JSON.toJSONString(userInfo), UserInfoEs.class));
        }
    }

    /***
     * 修改
     * @param before
     * @param after
     */
    @Override
    public void update(UserInfo before, UserInfo after) {
        System.out.println("update before---" + before);
        System.out.println("update after---" + after);
        if(after.getStatus().intValue() == 0){
            // 删除索引
            userInfoSearchFeign.del(after.getId().toString());
        } else {
            // 更新索引
            userInfoSearchFeign.add(JSON.parseObject(JSON.toJSONString(after), UserInfoEs.class));

        }
    }

    @Override
    public void delete(UserInfo userInfo) {
        System.out.println("delete---" + userInfo);
        userInfoSearchFeign.del(userInfo.getId().toString());
    }
}