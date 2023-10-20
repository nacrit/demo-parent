package com.example.demo.design_pattern.a_head_first_design_patterns.b_singleton.b_lazy;

/**
 * <p>
 * 懒汉式 单例模式,线程不安全
 * </p>
 *
 * @author zhenghao
 * @date 2020/12/16 17:45
 */
public class Singleton {

    private volatile static Singleton instance = null;

    private Singleton() {
    }

    // 双检锁/双重校验锁（DCL，即 double-checked locking）
    public static Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}
