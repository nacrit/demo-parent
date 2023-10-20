package com.example.demo.design_pattern.a_hfdp.b_singleton.b_lazy;

/**
 * 登记式/静态内部类
 */
public class Singleton2 {
    private static class SingletonHolder {
        private static final Singleton2 INSTANCE = new Singleton2();
    }

    private Singleton2() {
    }

    public static Singleton2 getInstance2() {
        return SingletonHolder.INSTANCE;
    }
}
