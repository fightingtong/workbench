package com.vv.work.search.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.Setting;

/**
 * 用户与员工关系索引
 * @author: tonghp
 * @date: 2022/7/5 17:51
 **/
@Data
@Document(indexName = "work_user_employee_index", type="useremployeerelationes")
@Setting(settingPath = "es/settings/pinyin_analyzer.json")
public class UserEmployeeRelationEs {
    /**
     * 唯一标识符（userCode + "_" + tenantCode）
     */
    @Id
    private String id;
    /**
     * 用户编码
     */
    @Field(type = FieldType.Keyword)
    private String userCode;
    /**
     * 用户昵称
     */
    @Field(type = FieldType.Text, analyzer = "pinyin_analyzer", searchAnalyzer = "pinyin_analyzer")
    private String nickName;
    /**
     * 租户编码
     */
    @Field(type = FieldType.Keyword)
    private String tenantCode;
    /**
     * 员工姓名
     */
    @Field(type = FieldType.Text, analyzer = "pinyin_analyzer", searchAnalyzer = "pinyin_analyzer")
    private String employeeName;
}
