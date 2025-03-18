package com.example.demo.design_pattern.a_head_first_design_patterns.singleton;

import com.example.demo.design_pattern.a_head_first_design_patterns.singleton.a_hungry.Singleton;
import com.example.demo.design_pattern.a_head_first_design_patterns.singleton.c_register.SingletonChild1;

/**
 * <p>
 * 单例模式测试
 * </p>
 *
 * @author zhenghao
 * @date 2020/12/16 17:59
 */
public class Main {
    public static void main(String[] args) {
        // 饿汉式
        System.out.println(Singleton.getInstance() == Singleton.getInstance());

        // 懒汉式
        System.out.println(
                com.example.demo.design_pattern.a_head_first_design_patterns.singleton.b_lazy.Singleton.getInstance()
                == com.example.demo.design_pattern.a_head_first_design_patterns.singleton.b_lazy.Singleton.getInstance()
        );

        // 注册表
        String className = com.example.demo.design_pattern.a_head_first_design_patterns.singleton.c_register.Singleton.class.getName();
        com.example.demo.design_pattern.a_head_first_design_patterns.singleton.c_register.Singleton.getInstance(className).test();

        SingletonChild1.getInstance().test();
    }
}
