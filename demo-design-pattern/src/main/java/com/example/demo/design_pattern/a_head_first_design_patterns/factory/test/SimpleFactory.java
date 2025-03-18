package com.example.demo.design_pattern.a_head_first_design_patterns.factory.test;

public class SimpleFactory {
    public static AbsProductA createAbsFactoryA(String name) {
        if (name.equals("A1")) {
            return new ProductA1();
        } else if (name.equals("A2")) {
            return new ProductA2();
        } else {
            throw new RuntimeException("not support factory name: " + name);
        }
    }
}
