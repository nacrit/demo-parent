package com.example.demo.design_pattern.a_head_first_design_patterns.factory.c_abstractfactory;

import com.example.demo.design_pattern.a_head_first_design_patterns.factory.Bmw;

//具体产品角色 -- 宝马跑车
public class BmwSportCar extends Bmw {

    @Override
    public void drive() {
        System.out.println("drive bmw sport car ...");
    }
}
