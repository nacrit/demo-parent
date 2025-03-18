package com.example.demo.design_pattern.a_head_first_design_patterns.factory.test;

public class FactoryA1 implements AbsFactoryA {
    @Override
    public AbsProductA createProductA() {
        return new ProductA1();
    }
}
