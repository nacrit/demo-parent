package com.example.demo.design_pattern.a_head_first_design_patterns.decorator;


// 装饰角色
public class DoorDecorator {
    protected Door door;
    public DoorDecorator(Door door) {
        this.door = door;
    }
    public void open() {
        door.open();
    }
    public void close() {
        door.close();
    }
    public void lock() {
        door.lock();
    }

    public void alarm() {
        System.out.println("alarm ...");
    }

}
