package com.example.demo.design_pattern.a_head_first_design_patterns.factory.c_abstractfactory;

import com.example.demo.design_pattern.a_head_first_design_patterns.factory.Car;

// 抽象工厂
public interface CarFactory {
    // 奔驰
    Car createBenCar();
    // 宝马
    Car createBmwCar();
}
