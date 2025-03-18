package com.example.demo.design_pattern.a_head_first_design_patterns.singleton.c_register;

/**
 * <p>
 * SingletonChild1
 * </p>
 *
 * @author zhenghao
 * @date 2020/12/16 18:05
 */
public class SingletonChild1 extends Singleton {
    public SingletonChild1() {
    }

    static public SingletonChild1 getInstance() {
        return (SingletonChild1) getInstance(SingletonChild1.class.getName());
    }

    public void test() {
        System.out.println("getclasssuccess111!");
    }
}
