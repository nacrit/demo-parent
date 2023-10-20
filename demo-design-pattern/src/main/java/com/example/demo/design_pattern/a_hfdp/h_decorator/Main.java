package com.example.demo.design_pattern.a_hfdp.h_decorator;

public class Main {
    public static void main(String[] args) {
        DoorDecorator alarmDoor = new AlarmDoor(new Door());
        alarmDoor.open();
        alarmDoor.close();
        alarmDoor.lock();
        // 装饰后新增功能
        alarmDoor.alarm();
    }
}
