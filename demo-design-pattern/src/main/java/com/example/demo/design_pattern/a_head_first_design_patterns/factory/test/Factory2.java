package com.example.demo.design_pattern.a_head_first_design_patterns.factory.test;

public class Factory2 implements AbsFactory {
    @Override
    public AbsProductA createProductA() {
        return new ProductA2();
    }

    @Override
    public AbsProductB createProductB() {
        return new ProductB2();
    }
}
