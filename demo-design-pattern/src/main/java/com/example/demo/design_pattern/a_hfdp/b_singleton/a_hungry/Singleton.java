package com.example.demo.design_pattern.a_hfdp.b_singleton.a_hungry;

/**
 * 饿汉式 单例模式
 */
public class Singleton {
    //在自己内部定义自己一个实例 //注意这是 private 只供内部调用
    private static final Singleton instance = new Singleton();

    //如上面所述，将构造函数设置为私有
    private Singleton() {
    }

    //静态工厂方法，提供了一个供外部访问得到对象的静态方法
    public static Singleton getInstance() {
        return instance;
    }
}