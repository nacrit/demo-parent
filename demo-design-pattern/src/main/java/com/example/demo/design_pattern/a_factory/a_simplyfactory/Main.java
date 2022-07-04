package com.example.demo.design_pattern.a_factory.a_simplyfactory;

import com.example.demo.design_pattern.a_factory.Car;

/**
 * <p>
 * 简单工厂模式又称静态工厂方法模式。
 * </p>
 *
 * @author zhenghao
 * @date 2020/12/16 15:18
 */
public class Main {
    public static void main(String[] args) throws Exception {
        // 告诉司机我今天坐奔驰
        Car car = Driver.driverCar("benz");
        // 下命令：开车
        car.drive();

        Driver.driverCar("bmw").drive();
    }
}

