package com.example.demo.design_pattern.a_head_first_design_patterns.bright;

/**
 * 后端
 */

//实现（Implementor）角色 -- 实现部分（后端）的实现角色
public interface Implementation {
    //这个接口只是定义了一定的接口
    void facility1();
    void facility2();
    void facility3();
    void facility4();
}

// 具体实现（ConcreteImplementor）角色就是要将实现角色提供的接口实现
//并完成一定的功能.这里省略了
class Implementation1 implements Implementation {
    @Override
    public void facility1() {
        System.out.println("facility1 ...");
    }

    @Override
    public void facility2() {
        System.out.println("facility2 ...");
    }

    @Override
    public void facility3() {
        System.out.println("facility3 ...");
    }

    @Override
    public void facility4() {
        System.out.println("facility4 ...");
    }
}