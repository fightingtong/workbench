package com.vv.work.search.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * @description:
 * @author: tonghp
 * @date: 2022/4/18 18:11
 **/
@Data
@Document(indexName = "worksearch5", type="userinfoes")
public class UserInfoEs {
    // 唯一标识符
    @Id
    private Integer id;
    // 用户名称
    @Field(type = FieldType.Text, analyzer = "pinyin_analyzer", searchAnalyzer = "pinyin_analyzer")
    private String name;
    // 年龄
    private Integer age;
    // 排序
    private Integer sort;
    // 状态
    private Integer status;
    // 分类
    @Field(type = FieldType.Keyword)
    private String category;
}
