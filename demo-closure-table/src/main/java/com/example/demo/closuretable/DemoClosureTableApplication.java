package com.example.demo.closuretable;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.demo.closuretable.mapper")
public class DemoClosureTableApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoClosureTableApplication.class, args);
    }

}
