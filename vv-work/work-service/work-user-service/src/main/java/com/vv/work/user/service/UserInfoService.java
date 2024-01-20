package com.vv.work.user.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.vv.work.user.model.UserInfo;

import java.util.List;

/**
 * @description:
 * @author: tonghp
 * @date: 2022/4/16 20:26
 **/
public interface UserInfoService extends IService<UserInfo> {

    List<UserInfo> queryList(UserInfo userInfo);

    Page<UserInfo> queryPageList(Long currentPage, Long size, UserInfo brand);
}
