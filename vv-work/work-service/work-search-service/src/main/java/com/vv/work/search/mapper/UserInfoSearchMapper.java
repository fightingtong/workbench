package com.vv.work.search.mapper;

import com.vv.work.search.model.UserInfoEs;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @description:
 * @author: tonghp
 * @date: 2022/4/18 23:29
 **/
public interface UserInfoSearchMapper extends ElasticsearchRepository<UserInfoEs, String> {
}
