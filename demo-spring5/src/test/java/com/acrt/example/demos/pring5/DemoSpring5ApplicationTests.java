package com.acrt.example.demos.pring5;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.SpringBootVersion;
import org.springframework.core.SpringVersion;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

//@SpringBootTest
//@RunWith(SpringRunner.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class DemoSpring5ApplicationTests {

    @Test
    public void contextLoads() {
        String springVersion = SpringVersion.getVersion();
        System.out.println(springVersion);
        String springBootVersion = SpringBootVersion.getVersion();
        System.out.println(springBootVersion);
        /*
        spring version：5.3.2
        spring boot version：2.4.1
         */
    }

}
