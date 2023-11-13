package com.example.demo.springboot.regist_bean;

import javax.annotation.Resource;

/**
 * @author mars
 * @description BeanB
 * @date 2023/11/13 15:29
 */
public abstract class AbsClassB {
    @Resource
    private BeanA beanA;

    private final String name;

    public AbsClassB() {
        this.name = this.getClass().getName();
    }

    public void say() {
        System.out.println("name = " + name);
        System.out.println("this.getClass().getName() = " + this.getClass().getName());
        System.out.println("this.beanA.getClass().getName() = " + this.beanA.getClass().getName());
    }
}
