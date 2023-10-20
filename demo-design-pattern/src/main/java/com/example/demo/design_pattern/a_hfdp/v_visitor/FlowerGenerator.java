package com.example.demo.design_pattern.a_hfdp.v_visitor;

import java.util.Random;


//这是一个对象生成器
//这不是一个完整的对象结构，这里仅仅是模拟对象结构中的元素
class FlowerGenerator {
    private static Random rand = new Random();
    public static Flower newFlower() {
        switch(rand.nextInt(3)) {
            default:
            case 0: return new Gladiolus();
            case 1: return new Runuculus();
            case 2: return new Chrysanthemum();
        }
    }
}
