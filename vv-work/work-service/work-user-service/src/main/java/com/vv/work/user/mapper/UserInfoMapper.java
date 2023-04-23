package com.vv.work.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.vv.work.user.model.UserInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserInfoMapper extends BaseMapper<UserInfo> {

    List<UserInfo> testMeetingInfo(@Param("category") String category);

    List<UserInfo> testMeetingInfo2(@Param("category") String category);

    List<UserInfo> testMeetingInfo3(@Param("category") String category);

    List<UserInfo> testMeetingInfo4(@Param("category") String category);

    List<UserInfo> testMeetingInfo5(@Param("category") String category);

    List<UserInfo> testMeetingInfo6(@Param("category") String category);

    List<UserInfo> testMeetingInfo7(@Param("category") String category);
}
