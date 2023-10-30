package com.example.demo.mybatis.dao;


import com.example.demo.mybatis.model.DemoMybatis;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoMybatisMapperTest {
    @Resource
    private DemoMybatisMapper demoMybatisMapper;

    @Test
    public void selectByPrimaryKey() throws InterruptedException {
        DemoMybatis demoMybatis = demoMybatisMapper.selectByPrimaryKey(1);
        while (demoMybatis != null) {
            System.out.println("demoMybatis：" + demoMybatis);
            Thread.sleep(1000);
            demoMybatis = demoMybatisMapper.selectByPrimaryKey(1);
        }
    }
}
