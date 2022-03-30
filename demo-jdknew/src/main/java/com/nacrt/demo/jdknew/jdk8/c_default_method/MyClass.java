package com.nacrt.demo.jdknew.jdk8.c_default_method;

/**
 * MyClass
 *
 * @author zhenghao
 * @date 2022/3/30 9:54
 */
public class MyClass implements MyInterface {
    @Override
    public void test1() {
        MyInterface.super.test1();
        System.out.println("MyClass.test1");
    }
}
