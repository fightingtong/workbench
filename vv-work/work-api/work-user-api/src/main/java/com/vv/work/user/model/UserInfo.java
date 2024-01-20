package com.vv.work.user.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
//MyBatisPlus表映射注解
@TableName("user_info")
@Table
public class UserInfo implements Serializable {
    // ID主键
    @TableId(type = IdType.AUTO)
    private Integer id;
    // 用户名称
    private String name;
    // 年龄
    private Integer age;
    // 排序
    private Integer sort;
    // 状态
    private Integer status;
    // 分类
    @Column(name = "category")
    private String category;
}
