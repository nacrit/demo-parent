package com.example.demo.design_pattern.a_hfdp.v_visitor;

import junit.framework.TestCase;
import junit.textui.TestRunner;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Main extends TestCase {
    /*
    在这里你能看到访问者模式执行的流程：
        首先在客户端先获得一个具体的访问者角色遍历对象结构对每一个元素调用 accept 方法，将具体访问者角色传入这样就完成了整个过程
    */
    // 对象结构角色在这里才组装上
    private List<Flower> flowers = new ArrayList<>();
    public Main() {
        for (int i = 0; i < 10; i++) {
            flowers.add(FlowerGenerator.newFlower());
        }
    }

    private Visitor visitor;

    public void test() {
        // It's almost as if I had a function to
        // produce a Flower string representation:
        //这个地方你可以修改以便使用另外一个具体访问者角色
        visitor = new StringVal();
        Iterator<Flower> it = flowers.iterator();
        while (it.hasNext()) {
            it.next().accept(visitor);
            System.out.println(visitor);
        }
    }
    public void test2() {
        // It's almost as if I had a function to
        // produce a Flower string representation:
        //这个地方你可以修改以便使用另外一个具体访问者角色
        visitor = new Bee();
        for (Flower flower : flowers) {
            flower.accept(visitor);
        }
    }

    public static void main(String args[]) {
        TestRunner.run(Main.class);
    }
}
