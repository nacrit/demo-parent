package com.example.demo.design_pattern.a_head_first_design_patterns.factory.test.main;

import com.example.demo.design_pattern.a_head_first_design_patterns.factory.test.SimpleFactory;

public class SimpleFactoryMain {
    public static void main(String[] args) {
        SimpleFactory.createAbsFactoryA("A1").say();
        SimpleFactory.createAbsFactoryA("A2").say();
//        SimpleFactory.createAbsFactoryA("A3").say();
    }
}
