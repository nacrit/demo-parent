package com.example.demo.design_pattern.a_head_first_design_patterns.factory.c_abstractfactory;

import com.example.demo.design_pattern.a_head_first_design_patterns.factory.Benz;

// 具体产品角色 -- 奔驰跑车
public class BenzSportCar extends Benz {

    @Override
    public void drive() {
        System.out.println("drive benz sport car ...");
    }
}
