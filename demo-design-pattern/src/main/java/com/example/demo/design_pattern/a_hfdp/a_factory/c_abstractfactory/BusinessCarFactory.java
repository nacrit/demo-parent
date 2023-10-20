package com.example.demo.design_pattern.a_hfdp.a_factory.c_abstractfactory;

import com.example.demo.design_pattern.a_hfdp.a_factory.Car;

// 具体工厂角色
public class BusinessCarFactory implements CarFactory {

    @Override
    public Car createBenCar() {
        return new BenzBusinessCar();
    }

    @Override
    public Car createBmwCar() {
        return new BmwBusinessCar();
    }
}
