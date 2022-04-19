package com.vv.work.search.service.impl;

import com.vv.work.search.mapper.UserInfoSearchMapper;
import com.vv.work.search.model.UserInfoEs;
import com.vv.work.search.service.UserInfoSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: tonghp
 * @date: 2022/4/18 23:38
 **/
@Service
public class UserInfoSearchServiceImpl implements UserInfoSearchService {
    @Autowired
    UserInfoSearchMapper userInfoSearchMapper;

    /**
     * 单个导入ES
     * @param userInfoEs
     */
    @Override
    public void add(UserInfoEs userInfoEs) {
        userInfoSearchMapper.save(userInfoEs);
    }

    /**
     * 根据ID删除
     * @param id
     */
    @Override
    public void del(String id) {
        userInfoSearchMapper.deleteById(id);
    }
}
