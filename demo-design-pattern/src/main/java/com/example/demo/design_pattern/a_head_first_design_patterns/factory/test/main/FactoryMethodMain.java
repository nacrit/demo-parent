package com.example.demo.design_pattern.a_head_first_design_patterns.factory.test.main;

import com.example.demo.design_pattern.a_head_first_design_patterns.factory.test.AbsFactoryA;
import com.example.demo.design_pattern.a_head_first_design_patterns.factory.test.AbsProductA;
import com.example.demo.design_pattern.a_head_first_design_patterns.factory.test.FactoryA1;
import com.example.demo.design_pattern.a_head_first_design_patterns.factory.test.FactoryA2;

public class FactoryMethodMain {
    public static void main(String[] args) {
        creatProduct(new FactoryA1()).say();
        creatProduct(new FactoryA2()).say();
    }

    public static AbsProductA creatProduct(AbsFactoryA factoryA) {
        return factoryA.createProductA();
    }
}
