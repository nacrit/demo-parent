package com.example.demo.design_pattern.a_head_first_design_patterns.factory.b_factorymethod;

import com.example.demo.design_pattern.a_head_first_design_patterns.factory.Car;

//抽象工厂角色 车工厂
interface CarFactory {
    // 创建车
    Car createCar();
}
