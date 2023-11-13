package com.example.demo.springboot.regist_bean;

/**
 * @author mars
 * @description GenericBean
 * @date 2023/11/13 15:31
 */
public class GenericBeanA extends AbsClassA {
    public GenericBeanA(String name) {
        setName(name);
        SpringBeanUtils.putBean(name, this);
    }
}
