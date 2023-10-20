package com.example.demo.design_pattern.a_head_first_design_patterns.a_factory.c_abstractfactory;

import com.example.demo.design_pattern.a_head_first_design_patterns.a_factory.Benz;

//具体产品角色 -- 奔驰商务车
public class BenzBusinessCar extends Benz {

    @Override
    public void drive() {
        System.out.println("drive benz bussiness car ...");
    }
}
