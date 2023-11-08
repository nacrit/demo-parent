package com.example.demo.mistakes.demo03;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ThreadPoolDemoTest {
    @Resource
    private ThreadPoolDemo threadPoolDemo;

    @Test
    public void oom1() throws InterruptedException {
        threadPoolDemo.oom1();
    }

    @Test
    public void myDefineThreadPool() throws InterruptedException {
//        System.out.println("共成功：" + threadPoolDemo.right());
    }
}