package com.acrt.example.demos.pring5.tt_temp;

import com.acrt.example.demos.pring5.tt_temp.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * <p>
 * 注解形式操作
 * </p>
 *
 * @author zhenghao
 * @date 2021/1/13 17:13
 */
public class TempTest02 {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("tt-bean-02.xml");
        UserService userService = applicationContext.getBean(UserService.class);
        userService.sayHello();
    }
}
