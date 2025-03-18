package com.example.demo.design_pattern.a_head_first_design_patterns.factory.test;

public class FactoryA2 implements AbsFactoryA {
    @Override
    public AbsProductA createProductA() {
        return new ProductA2();
    }
}
