package com.vv.work.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @description:
 * @author: tonghp
 * @date: 2022/4/16 23:24
 **/
@Configuration
public class StartConfig {
    /**
     * Mybatis分页拦截器
     * 必须定义Bean，否则分页无效
     * @return
     */
    @Bean
    public PaginationInterceptor paginationInterceptor(){
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        // 设置数据类型
        paginationInterceptor.setDbType(DbType.MYSQL);
        return paginationInterceptor;
    }
}
