package com.example.demo.design_pattern.a_head_first_design_patterns.decorator;

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
