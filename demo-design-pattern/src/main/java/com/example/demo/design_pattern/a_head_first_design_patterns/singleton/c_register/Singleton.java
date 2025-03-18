package com.example.demo.design_pattern.a_head_first_design_patterns.singleton.c_register;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 采用带有注册表 的方式 单例模式
 * </p>
 *
 * @author zhenghao
 * @date 2020/12/16 17:56
 */
public class Singleton {
    //用来存放对应关系
    private static Map<String, Object> sinRegistry = new HashMap<>();
    private static Singleton instance = new Singleton();

    //受保护的构造函数
    protected Singleton() {
    }

    public static synchronized Singleton getInstance(String name) {
        if (name == null) {
            return instance;
        }
        if (sinRegistry.get(name) == null) {
            try {
                sinRegistry.put(name, Class.forName(name).newInstance());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return (Singleton) (sinRegistry.get(name));
    }

    public void test() {
        System.out.println("get class success!");
    }
}
