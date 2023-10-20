package com.example.demo.design_pattern.a_hfdp.a_factory.b_factorymethod;

import com.example.demo.design_pattern.a_hfdp.a_factory.Bmw;
import com.example.demo.design_pattern.a_hfdp.a_factory.Car;

// 具体工厂角色 宝马车工厂
class BmwCarFactory implements CarFactory {
    public Car createCar() {
        return new Bmw();
    }
}
