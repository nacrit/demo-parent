package com.example.demo.design_pattern.a_head_first_design_patterns.factory.b_factorymethod;

import com.example.demo.design_pattern.a_head_first_design_patterns.factory.Benz;
import com.example.demo.design_pattern.a_head_first_design_patterns.factory.Car;

// 具体工厂角色 奔驰车工厂
class BenzCarFactory implements CarFactory {
    public Car createCar() {
        return new Benz();
    }
}
