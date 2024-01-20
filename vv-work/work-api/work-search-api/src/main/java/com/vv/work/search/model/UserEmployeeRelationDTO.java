package com.vv.work.search.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 用户与员工关系索引
 * @author: tonghp
 * @date: 2022/7/5 17:51
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserEmployeeRelationDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户编码
     */
    @NotBlank(message = "用户编码不可为空")
    private String userCode;
    /**
     * 用户昵称
     */
    @NotBlank(message = "用户昵称不可为空")
    private String nickName;
    /**
     * 租户编码
     */
    @NotBlank(message = "租户编码不可为空")
    private String tenantCode;
    /**
     * 员工姓名
     */
    @NotBlank(message = "员工姓名不可为空")
    private String employeeName;
}
