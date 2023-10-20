package com.example.demo.design_pattern.a_head_first_design_patterns.d_prototype;

// 客户角色
public class Client {
    public void prototype() throws CloneNotSupportedException {
        //先 new 一个具体原型角色作为样本
        Prototype p = new ConcretePrototype();
        //使用原型 p 克隆出一个新对象 p1
        Prototype p1 = (Prototype)p.clone();

    }
}
