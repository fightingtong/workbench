package com.vv.work.search.service.impl;

import com.vv.work.search.mapper.UserInfoSearchMapper;
import com.vv.work.search.model.UserInfoEs;
import com.vv.work.search.service.UserInfoSearchService;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: tonghp
 * @date: 2022/4/18 23:38
 **/
@Service
public class UserInfoSearchServiceImpl implements UserInfoSearchService {
    @Autowired
    UserInfoSearchMapper userInfoSearchMapper;

    /**
     * 单个导入ES
     * @param userInfoEs
     */
    @Override
    public void add(UserInfoEs userInfoEs) {
        userInfoSearchMapper.save(userInfoEs);
    }

    /**
     * 根据ID删除
     * @param id
     */
    @Override
    public void del(String id) {
        userInfoSearchMapper.deleteById(id);
    }

    /***
     * 商品搜索
     * @param searchMap
     * @return
     */
    @Override
    public Map<String, Object> search(Map<String, Object> searchMap) {
        //条件封装
        NativeSearchQueryBuilder queryBuilder = queryBuilder2(searchMap);

        //执行搜索
        Page<UserInfoEs> result = userInfoSearchMapper.search(queryBuilder.build());

        //结果集
        List<UserInfoEs> list = result.getContent();
        //中记录数
        long totalElements = result.getTotalElements();

        Map<String,Object> resultMap = new HashMap<String,Object>();
        resultMap.put("list",list);
        resultMap.put("totalElements",totalElements);
        return resultMap;
    }

    /***
     * 搜索条件组装
     */
    private NativeSearchQueryBuilder queryBuilder(Map<String,Object> searchMap){
        //QueryBuilder构建
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();

        //条件判断
        if(searchMap!=null && searchMap.size()>0){
            //关键词
            Object keywords =searchMap.get("keywords");
            if(!StringUtils.isEmpty(keywords)){
                queryBuilder.withQuery(QueryBuilders.matchPhraseQuery("name",keywords.toString()));
            }
        }
        return queryBuilder;
    }

    /***
     * 搜索多条件组装
     */
    private NativeSearchQueryBuilder queryBuilder2(Map<String,Object> searchMap){
        //QueryBuilder构建
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();

        //条件判断
        if(searchMap!=null && searchMap.size()>0){

            BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
            //关键词
            Object keywords =searchMap.get("keywords");
            if(!StringUtils.isEmpty(keywords)){
                boolQueryBuilder.must(QueryBuilders.matchPhraseQuery("name",keywords.toString()));
            }
            Object category =searchMap.get("category");
            if(!StringUtils.isEmpty(category)){
                boolQueryBuilder.must(QueryBuilders.termQuery("category",category.toString()));
            }
            if(boolQueryBuilder.hasClauses()){
                queryBuilder.withQuery(boolQueryBuilder);
            }
        }
        return queryBuilder;
    }
}
