package com.example.demo.springboot.regist_bean;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author mars
 * @description BeanB
 * @date 2023/11/13 15:29
 */
@Data
@Component // 没有实现类，此注解不生效
public abstract class AbsClassA {
    @Resource
    private BeanA beanA;

    private String name;

    public AbsClassA() {
        this.name = this.getClass().getName();
    }

    public void say() {
        System.out.println("name = " + name);
        System.out.println("this.getClass().getName() = " + this.getClass().getName());
        System.out.println("this.beanA.getClass().getName() = " + this.beanA.getClass().getName());
    }
}
