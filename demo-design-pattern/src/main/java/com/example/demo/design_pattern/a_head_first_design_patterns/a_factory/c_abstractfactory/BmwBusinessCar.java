package com.example.demo.design_pattern.a_head_first_design_patterns.a_factory.c_abstractfactory;

import com.example.demo.design_pattern.a_head_first_design_patterns.a_factory.Bmw;

//具体产品角色 -- 宝马商务车
public class BmwBusinessCar extends Bmw {

    @Override
    public void drive() {
        System.out.println("drive bmw bussiness car ...");
    }
}
