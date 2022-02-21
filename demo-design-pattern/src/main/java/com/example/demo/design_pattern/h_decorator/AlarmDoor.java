package com.example.demo.design_pattern.h_decorator;

// 具体装饰角色
public class AlarmDoor extends DoorDecorator {
    public AlarmDoor(Door door) {
        super(door);
    }

    @Override
    public void alarm() {
        System.out.println("-----------");
        super.alarm();
        System.out.println("-----------");
    }
}
