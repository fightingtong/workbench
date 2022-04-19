package com.vv.work.canal;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @description:
 * exclude移除数据库自动加载
 * @author: tonghp
 * @date: 2022/4/18 17:38
 **/
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableFeignClients(basePackages = {"com.vv.work.search.feign"})
public class WorkCanalApplication {
    public static void main(String[] args){
        SpringApplication.run(WorkCanalApplication.class, args);
    }
}
