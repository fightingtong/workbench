package com.vv.work.user.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.annotation.Target;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("user_info")
public class UserInfo {
    // ID主键
    @TableId(type = IdType.AUTO)
    private Integer id;
    // 用户名称
    private String name;
    // 年龄
    private Integer age;
    // 排序
    private Integer sort;
}
