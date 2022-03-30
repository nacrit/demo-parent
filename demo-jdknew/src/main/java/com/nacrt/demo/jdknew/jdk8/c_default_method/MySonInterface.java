package com.nacrt.demo.jdknew.jdk8.c_default_method;

/**
 * MySonInterface
 *
 * @author zhenghao
 * @date 2022/3/30 9:59
 */
public interface MySonInterface extends MyInterface {
    default void test3() {
        System.out.println("MySonInterface.test3");
    }
}
