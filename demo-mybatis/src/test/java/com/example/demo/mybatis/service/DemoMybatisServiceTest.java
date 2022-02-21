package com.example.demo.mybatis.service;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoMybatisServiceTest {
    @Resource
    private DemoMybatisService demoMybatisService;

    @Test
    public void find() {
//        demoMybatisService.testTra();
        demoMybatisService.retryMethod(1);
//        demoMybatisService.testRetryMethod(null);
    }
}
