package com.example.demo.design_pattern.a_hfdp.a_factory.b_factorymethod;

import com.example.demo.design_pattern.a_hfdp.a_factory.Benz;
import com.example.demo.design_pattern.a_hfdp.a_factory.Car;

// 具体工厂角色 奔驰车工厂
class BenzCarFactory implements CarFactory {
    public Car createCar() {
        return new Benz();
    }
}
