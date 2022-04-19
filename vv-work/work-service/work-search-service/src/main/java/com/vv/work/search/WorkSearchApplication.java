package com.vv.work.search;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

/**
 * @description:
 * @author: tonghp
 * @date: 2022/4/18 19:32
 **/
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableElasticsearchRepositories(basePackages = "com.vv.work.search.mapper")
public class WorkSearchApplication {
    public static void main(String[] args){
        SpringApplication.run(WorkSearchApplication.class, args);
    }
}
