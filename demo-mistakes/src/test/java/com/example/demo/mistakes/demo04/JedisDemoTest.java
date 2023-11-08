package com.example.demo.mistakes.demo04;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JedisDemoTest {

    @Resource
    private JedisDemo jedisDemo;

    @Test
    public void demo1Test() throws InterruptedException {
        jedisDemo.wrong();
//        jedisDemo.right();
    }

}