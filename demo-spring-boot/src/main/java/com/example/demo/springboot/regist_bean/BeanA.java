package com.example.demo.springboot.regist_bean;

import org.springframework.stereotype.Component;

/**
 * @author mars
 * @description BeanA
 * @date 2023/11/13 15:28
 */
@Component
public class BeanA {
    public void say() {
        System.out.println("this.getClass().getName() = " + this.getClass().getName());
    }
}
