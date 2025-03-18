package com.example.demo.design_pattern.a_head_first_design_patterns.factory.c_abstractfactory;

/**
 * <p>
 * 抽象工厂模式
 * BmwCar
 *  BmwBusinessCar
 *  BmwSportCar
 * BenzCar
 *  BenzBusinessCar
 *  BenzSportCar
 * SportCar:跑车
 *  BmwSportCar
 *  BenzSportCar
 * BusinessCar:商务车
 *  BmwBusinessCar
 *  BenzBusinessCar
 * </p>
 *
 * @author zhenghao
 * @date 2020/12/16 16:05
 */
public class Main {
    public static void main(String[] args) {
        // SportCar
        SportCarFactory sportCarFactory = new SportCarFactory();
        sportCarFactory.createBenCar().drive(); // BenSportCar
        sportCarFactory.createBmwCar().drive(); // BmwSportCar
        // BusinessCar
        BusinessCarFactory businessCarFactory = new BusinessCarFactory();
        businessCarFactory.createBenCar().drive(); // BenBusinessCar
        businessCarFactory.createBmwCar().drive(); // BmwBusinessCar
    }
}
