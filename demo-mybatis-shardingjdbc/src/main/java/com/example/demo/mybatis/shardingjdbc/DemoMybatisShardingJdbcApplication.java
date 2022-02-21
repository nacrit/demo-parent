package com.example.demo.mybatis.shardingjdbc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.demo.mybatis.shardingjdbc.mapper")
public class DemoMybatisShardingJdbcApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoMybatisShardingJdbcApplication.class, args);
    }

}
