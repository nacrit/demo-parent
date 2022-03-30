package com.nacrt.demo.jdknew.jdk8.c_default_method;

/**
 * test
 *
 * @author zhenghao
 * @date 2022/3/30 9:53
 */
public class DemoDefaultMethod {
    /*
    为什么要有默认方法这个特性？
    首先，之前的接口是个双刃剑，好处是面向抽象而不是面向具体编程，缺陷是，当需要修改接口时候，需要修改全部实现该接口的类，
    目前的 java 8 之前的集合框架没有 foreach 方法，通常能想到的解决办法是在JDK里给相关的接口添加新的方法及实现。
    然而，对于已经发布的版本，是没法在给接口添加新方法的同时不影响已有的实现。所以引进的默认方法。
    他们的目的是为了解决接口的修改与现有的实现不兼容的问题。
     */

    public static void main(String[] args) {
        test1();
        test2();
    }

    // 如果一个类C实现A、B两个接口，并且有相同默认方法m，那么类C必须也要实现m，可使用A.super.m()调用父接口
    public static void test2() {
        new C(){}.m();
    }

    // 接口、类的静态字段可通过子类（或对象）调用，接口的静态方法子类都不能调用
    public static void test1() {
        // 1. 接口的默认方法，静态字段，子类都可调用
        MyClass myClass = new MyClass();
        myClass.test1();
        myClass.test2();
        System.out.println("MyInterface.ONE = " + MyInterface.ONE);
        System.out.println("MySonInterface.ONE = " + MySonInterface.ONE);
        System.out.println("myClass.ONE = " + myClass.ONE);
        System.out.println("MyClass.ONE = " + MyClass.ONE);
        // 2. 接口的静态方法只有子接口可使用，实现类不可调用
        MyInterface.testStatic1();
        MyInterface.testStatic2();
        // myClass.testStatic1(); //此语句编译时报错
        // MySonInterface.testStatic1();  //此语句编译时报错
        // new MySonInterface(){}.testStatic1();  //此语句编译时报错
    }

}
