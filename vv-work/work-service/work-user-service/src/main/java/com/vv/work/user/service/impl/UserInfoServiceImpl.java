package com.vv.work.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vv.work.user.mapper.UserInfoMapper;
import com.vv.work.user.model.UserInfo;
import com.vv.work.user.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @description:
 * @author: tonghp
 * @date: 2022/4/16 20:27
 **/
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {

    @Autowired
    UserInfoMapper userInfoMapper;

    /****
     * 多条件查询
     * @param userInfo
     * @return
     */
    @Override
    public List<UserInfo> queryList(UserInfo userInfo) {
        // 多条件构造器
        QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<UserInfo>();
        if(!StringUtils.isEmpty(userInfo.getName())){
            queryWrapper.like("name",userInfo.getName());
        }
        if(!StringUtils.isEmpty(userInfo.getName())){
            queryWrapper.eq("age",userInfo.getAge());
        }
        return userInfoMapper.selectList(queryWrapper);
    }

    /***
     * 分页查询
     * @param brand
     * @return
     */
    @Override
    public Page<UserInfo> queryPageList(Long currentPage, Long size, UserInfo brand) {
        // 封装查询条件
        Page<UserInfo> page = userInfoMapper.selectPage(
                new Page<UserInfo>(currentPage, size),
                new QueryWrapper<UserInfo>()
                        .like("name", brand.getName()));
        return page;
    }
}
