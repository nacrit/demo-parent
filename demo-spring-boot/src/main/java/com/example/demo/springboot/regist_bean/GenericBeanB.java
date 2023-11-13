package com.example.demo.springboot.regist_bean;

/**
 * @author mars
 * @description GenericBean
 * @date 2023/11/13 15:31
 */
public class GenericBeanB extends AbsClassA {
    public GenericBeanB(String name) {
        setName(name);
        setBeanA(SpringBeanUtils.getBean(BeanA.class));
        SpringBeanUtils.putBean(name, this);
    }
}
