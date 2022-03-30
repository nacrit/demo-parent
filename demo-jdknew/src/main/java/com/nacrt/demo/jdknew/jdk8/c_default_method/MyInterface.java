package com.nacrt.demo.jdknew.jdk8.c_default_method;

/**
 * 我的接口
 *
 * @author zhenghao
 * @date 2022/3/30 9:46
 */
public interface MyInterface {
    int ONE = 1;

    // 默认方法1，典型的又 java.lang.Iterable#forEach
    default void test1() {
        System.out.println("MyInterface.test1");
    }

    // 默认方法2
    default void test2() {
        System.out.println("MyInterface.test2");
    }

    static void testStatic1() {
        System.out.println("MyInterface.testStatic1");
    }

    static void testStatic2() {
        System.out.println("MyInterface.testStatic2");
    }
}
