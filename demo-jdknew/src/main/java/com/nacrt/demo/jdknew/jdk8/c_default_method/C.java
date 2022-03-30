package com.nacrt.demo.jdknew.jdk8.c_default_method;

/**
 * C
 *
 * @author zhenghao
 * @date 2022/3/30 10:22
 */
public interface C extends A, B {

    // 此方法必须要实现
    @Override
    default void m() {
        A.super.m();
        B.super.m();
        System.out.println("C.m");
    }
}

interface A {
    default void m() {
        System.out.println("A.m");
    }
}

interface B {
    default void m() {
        System.out.println("B.m");
    }
}