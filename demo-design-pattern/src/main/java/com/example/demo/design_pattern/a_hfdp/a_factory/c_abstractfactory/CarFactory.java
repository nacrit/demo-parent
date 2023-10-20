package com.example.demo.design_pattern.a_hfdp.a_factory.c_abstractfactory;

import com.example.demo.design_pattern.a_hfdp.a_factory.Car;

// 抽象工厂
public interface CarFactory {
    // 奔驰
    Car createBenCar();
    // 宝马
    Car createBmwCar();
}
