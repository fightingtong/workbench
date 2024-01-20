package com.vv.work;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@MapperScan(basePackages = {"com.vv.work.user.mapper"})
public class WorkUserApplication {
    public static void main(String[] args){
        SpringApplication.run(WorkUserApplication.class, args);
    }
}
