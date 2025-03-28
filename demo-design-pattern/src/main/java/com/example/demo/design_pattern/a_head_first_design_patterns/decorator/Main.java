package com.example.demo.design_pattern.a_head_first_design_patterns.decorator;

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
