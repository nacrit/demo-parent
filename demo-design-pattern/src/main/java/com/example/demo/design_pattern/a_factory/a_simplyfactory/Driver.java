package com.example.demo.design_pattern.a_factory.a_simplyfactory;


import com.example.demo.design_pattern.a_factory.Benz;
import com.example.demo.design_pattern.a_factory.Bmw;
import com.example.demo.design_pattern.a_factory.Car;

//工厂类角色
public class Driver { //工厂方法.注意 返回类型为抽象产品角色
    public static Car driverCar(String s) throws Exception {
        //判断逻辑，返回具体的产品角色给 Client
        if (s.equalsIgnoreCase("Benz")) {
            return new Benz();
        } else if (s.equalsIgnoreCase("Bmw")) {
            return new Bmw();
        } else {
            throw new Exception();
        }
    }
}
