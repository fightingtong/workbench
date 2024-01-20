package com.vv.work.search.service;


import com.vv.work.search.model.UserEmployeeRelationDTO;

import java.util.Map;

/**
 * 用户与员工关系索引
 * @author: tonghp
 * @date: 2022/7/6 10:17
 **/
public interface UserEmployeeRelationSearchService {

    /***
     * 企业联系人搜索
     * @param searchMap
     * @return
     */
    Map<String,Object> search(Map<String, Object> searchMap);

    /**
     * 添加索引
     * @param dto
     */
    void add(UserEmployeeRelationDTO dto);
}
