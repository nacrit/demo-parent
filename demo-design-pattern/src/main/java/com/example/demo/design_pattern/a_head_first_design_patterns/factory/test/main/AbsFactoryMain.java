package com.example.demo.design_pattern.a_head_first_design_patterns.factory.test.main;

import com.example.demo.design_pattern.a_head_first_design_patterns.factory.test.AbsFactory;
import com.example.demo.design_pattern.a_head_first_design_patterns.factory.test.Factory1;
import com.example.demo.design_pattern.a_head_first_design_patterns.factory.test.Factory2;

public class AbsFactoryMain {
    public static void main(String[] args) {
        creatProduct(new Factory1());
        creatProduct(new Factory2());
    }

    public static void creatProduct(AbsFactory factory) {
        factory.createProductA().say();
        factory.createProductB().say();
    }

}
