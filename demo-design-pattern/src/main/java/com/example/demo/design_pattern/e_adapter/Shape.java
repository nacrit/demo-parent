package com.example.demo.design_pattern.e_adapter;

//目标（Target）角色
abstract class Shape {
    abstract void display();
}
// 点
class Point extends Shape {
    @Override
    void display() {
        System.out.println("············");
    }
}
// 行
class Line extends Shape {
    @Override
    void display() {
        System.out.println("一一一一一一一");
    }
}
// 正方形的
class Square extends Shape {
    @Override
    void display() {
        System.out.println("口口口口口口口");
    }
}

// 圆（适配器）
class Circle extends Shape {
    // 这里引用了 TextCircle
    private TextCircle tc;
    public Circle () {
        tc= new TextCircle(); //初始化
    }
    public void display() {
        tc.displayIt(); //在规定的方法里面调用 TextCircle 原来的方法
    }
}