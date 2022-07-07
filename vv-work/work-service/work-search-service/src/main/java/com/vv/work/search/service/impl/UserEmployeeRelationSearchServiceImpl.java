package com.vv.work.search.service.impl;

import com.vv.work.search.mapper.UserEmployeeRelationSearchMapper;
import com.vv.work.search.model.UserEmployeeRelationDTO;
import com.vv.work.search.model.UserEmployeeRelationEs;
import com.vv.work.search.service.UserEmployeeRelationSearchService;
import com.vv.work.search.util.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 用户与员工关系索引
 * @author: tonghp
 * @date: 2022/7/6 10:26
 **/
@Service
public class UserEmployeeRelationSearchServiceImpl implements UserEmployeeRelationSearchService {

    @Autowired
    private UserEmployeeRelationSearchMapper userEmployeeRelationSearchMapper;

    @Override
    public Map<String, Object> search(Map<String, Object> searchMap) {
        return null;
    }

    @Override
    public void add(UserEmployeeRelationDTO dto) {
        UserEmployeeRelationEs es = BeanUtil.copy(dto, UserEmployeeRelationEs.class);
        String id = String.format("%s_%s", es.getUserCode(), es.getTenantCode());
        es.setId(id);
        userEmployeeRelationSearchMapper.save(es);
    }
}
