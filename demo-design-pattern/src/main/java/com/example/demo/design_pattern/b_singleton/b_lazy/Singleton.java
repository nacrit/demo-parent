package com.example.demo.design_pattern.b_singleton.b_lazy;

/**
 * <p>
 * 懒汉式 单例模式,线程不安全
 * </p>
 *
 * @author zhenghao
 * @date 2020/12/16 17:45
 */
public class Singleton {

    private static Singleton instance  = null;

    private Singleton() {
    }

    // 为防止多线程安全 加上了同步
    public static synchronized Singleton getInstance() {
        if (instance  == null) {
            instance  = new Singleton();
        }
        return instance ;
    }
}
