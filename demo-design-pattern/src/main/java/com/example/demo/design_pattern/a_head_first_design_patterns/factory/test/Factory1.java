package com.example.demo.design_pattern.a_head_first_design_patterns.factory.test;

public class Factory1 implements AbsFactory {
    @Override
    public AbsProductA createProductA() {
        return new ProductA1();
    }

    @Override
    public AbsProductB createProductB() {
        return new ProductB1();
    }
}
