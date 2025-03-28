package com.example.demo.design_pattern.a_head_first_design_patterns.prototype;

// 抽象原型角色
public abstract class Prototype implements Cloneable {
    @Override
    protected Object clone() {
        Object clone = null;
        try {
            clone = super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return clone;
    }
}
