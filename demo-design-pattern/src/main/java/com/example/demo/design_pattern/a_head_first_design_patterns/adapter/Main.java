package com.example.demo.design_pattern.a_head_first_design_patterns.adapter;

public class Main {
    public static void main(String[] args) {
        display(new Line());
        display(new Point());
        display(new Square());
        // 适配后的圆
        display(new Circle());
    }

    private static void display(Shape shape) {
        shape.display();
    }
}
