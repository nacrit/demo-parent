package com.acrt.example.demos.pring5.aa_introductory;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * <p>
 * spring5入门学习
 * </p>
 *
 * @author zhenghao
 * @date 2021/1/13 16:35
 */
public class Spring5tTest {
    public static void main(String[] args) {
        // 加载配置及，启动容器
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("aa-bean-01.xml");
        // 获取容器中的bean
        User user = applicationContext.getBean("user", User.class);
        // 操作bean
        System.out.println(user);
        user.sayHello();
    }
}
