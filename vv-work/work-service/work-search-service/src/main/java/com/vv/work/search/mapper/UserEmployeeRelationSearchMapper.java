package com.vv.work.search.mapper;

import com.vv.work.search.model.UserEmployeeRelationEs;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * 用户与员工关系索引Mapper
 * @author: tonghp
 * @date: 2022/7/5 19:08
 **/
public interface UserEmployeeRelationSearchMapper extends ElasticsearchRepository<UserEmployeeRelationEs, String> {
}
