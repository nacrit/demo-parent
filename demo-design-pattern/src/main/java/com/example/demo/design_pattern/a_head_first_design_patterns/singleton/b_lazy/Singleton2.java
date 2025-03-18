package com.example.demo.design_pattern.a_head_first_design_patterns.singleton.b_lazy;

/**
 * 登记式/静态内部类
 */
public class Singleton2 {
    private static class SingletonHolder {
        private static final Singleton2 INSTANCE = new Singleton2();
    }

    private Singleton2() {
    }

    public static Singleton2 getInstance() {
        return SingletonHolder.INSTANCE;
    }
}
