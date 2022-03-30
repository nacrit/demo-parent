package com.nacrt.demo.jdknew.jdk8.a_lambda;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

/**
 * 函数式接口demo
 *
 * @author zhenghao
 * @date 2022/3/30 10:28
 */
public class Demo00FunctionalInterface {

    public static void main(String[] args) {
        // 函数时 --> 方法引用
        BiConsumer<MyFunInter, Integer> test = MyFunInter::test;
        // lambda表达式实现函数式接口
        test.accept(i -> System.out.println("MyFunInter.test, i=" + i), 123);

    }
    // 函数时接口：1.@FunctionalInterface 2.只有一个抽象方法
    @FunctionalInterface
    public interface MyFunInter {
        // 抽象方法只能有一个
        void test(int i);
        // 父类方法可以有
        String toString();
        // 默认方法可以有
        default void m1() {
            System.out.println("MyFunInter.m1");
        }
        // 静态方法可以有
        static void ms1() {
            System.out.println("MyFunInter.ms1");
        }
    }
}
