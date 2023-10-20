package com.example.demo.design_pattern.a_hfdp.f_bright;

public class Main {
    public static void main(String[] args) {
        // 精确抽象角色1
        Abstraction abstraction = new ClientService1(new Implementation1());
        abstraction.service1();
        abstraction.service2();
        abstraction.service3();

        // 精确抽象角色2
        abstraction = new ClientService2(new Implementation1());
        abstraction.service1();
        abstraction.service2();
        abstraction.service3();
    }
}
