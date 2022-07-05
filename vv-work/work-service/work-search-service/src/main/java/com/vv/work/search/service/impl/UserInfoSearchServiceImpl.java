package com.vv.work.search.service.impl;

import com.vv.work.search.mapper.UserInfoSearchMapper;
import com.vv.work.search.model.UserInfoEs;
import com.vv.work.search.service.UserInfoSearchService;
import com.vv.work.search.util.HighlightResultMapper;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.SortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
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
    private UserInfoSearchMapper userInfoSearchMapper;

    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

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
        //UserInfoEs userInfoEs = new UserInfoEs();
        //userInfoEs.setId(11);
        //userInfoSearchMapper.delete(userInfoEs);
    }

    /***
     * 商品搜索
     * @param searchMap
     * @return
     */
    @Override
    public Map<String, Object> search(Map<String, Object> searchMap) {
        //构建搜索条件
        NativeSearchQueryBuilder queryBuilder = queryBuilder2(searchMap);

        //1.设置高亮信息   关键词前（后）面的标签、设置高亮域
        HighlightBuilder.Field field = new HighlightBuilder
                .Field("name")  //根据指定的域进行高亮查询
                .preTags("<span>")     //关键词高亮前缀
                .postTags("</span>")   //高亮关键词后缀
                .fragmentSize(100);     //碎片长度
        queryBuilder.withHighlightFields(field);
        // 2、将非高亮数据替换成高亮数据

        //执行搜索
        //Page<UserInfoEs> result = userInfoSearchMapper.search(queryBuilder.build());
        // 为实现高亮，修改执行搜索的方式
        AggregatedPage<UserInfoEs> result = elasticsearchRestTemplate.queryForPage(queryBuilder.build(), UserInfoEs.class, new HighlightResultMapper());

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
            // 按score排序，需要使用 functionScoreQuery进行包装
            FunctionScoreQueryBuilder functionScoreQueryBuilder = QueryBuilders.functionScoreQuery(boolQueryBuilder);
            if(boolQueryBuilder.hasClauses()){
                queryBuilder.withQuery(functionScoreQueryBuilder);
            }
            queryBuilder.withSort(SortBuilders.scoreSort());
            // 分页， 取前20条
            queryBuilder.withPageable(PageRequest.of(0, 20));
        }
        return queryBuilder;
    }
}
