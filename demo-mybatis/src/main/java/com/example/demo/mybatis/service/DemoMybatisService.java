package com.example.demo.mybatis.service;

import com.example.demo.mybatis.model.DemoMybatis;

/**
 * @author zhenghao
 * @description Test
 * @date 2020/7/9 9:30
 */
public interface DemoMybatisService {
    DemoMybatis find(Integer id);
    boolean modify(DemoMybatis demoMybatis);
//    boolean tempUpdate(Integer id,
//                       String name);
    void testTra();

    void retryMethod(Integer id);
//    void testRetryMethod(String name);
}
