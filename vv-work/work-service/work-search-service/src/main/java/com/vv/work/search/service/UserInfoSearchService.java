package com.vv.work.search.service;

import com.vv.work.search.model.UserInfoEs;

import java.util.Map;

/**
 * @description:
 * @author: tonghp
 * @date: 2022/4/18 23:36
 **/
public interface UserInfoSearchService {
    //添加索引
    void add(UserInfoEs userInfoEs);
    //删除索引
    void del(String id);

    /***
     * 用户搜索
     * @param searchMap
     * @return
     */
    Map<String,Object> search(Map<String, Object> searchMap);
}
