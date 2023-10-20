package com.example.demo.design_pattern.a_head_first_design_patterns.a_factory.b_factorymethod;

import com.example.demo.design_pattern.a_head_first_design_patterns.a_factory.Car;

/**
 * <p>
 * 工厂方法模式去掉了简单工厂模式中工厂方法的静态属性，使得它可以被子类继承。这 样在简单工厂模式里集中在工厂方法上的压力可以由工厂方法模式里不同的工厂子类来分 担
 * </p>
 *
 * @author zhenghao
 * @date 2020/12/16 15:34
 */
public class Main {
    public static void main(String[] args) {
        //应该和具体产品形成对应关系...
        // 有请暴发户先生
        try {
            CarFactory driver = new BenzCarFactory();
            Car car = driver.createCar();
            car.drive();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            CarFactory driver = new BmwCarFactory();
            Car car = driver.createCar();
            car.drive();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

