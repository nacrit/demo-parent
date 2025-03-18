package com.example.demo.design_pattern.a_head_first_design_patterns.factory.c_abstractfactory;

import com.example.demo.design_pattern.a_head_first_design_patterns.factory.Car;

// 具体工厂角色
public class SportCarFactory implements CarFactory {

    @Override
    public Car createBenCar() {
        return new BenzSportCar();
    }

    @Override
    public Car createBmwCar() {
        return new BmwSportCar();
    }
}
