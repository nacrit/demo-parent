package com.example.demo.design_pattern.q_memento;

//测试代码——客户程序
public class Main {
    public static void main(String[] args) {
        // 备忘录发起者角色
        Originator o = new Originator();
        // 创建一个备忘录角色，并将当前状态属性存入，托给“备忘录管理者角色”存放。
        o.createMemento();
        // 修改装填
        o.modifyState4Test(80);
        // 读取备忘录恢复以前的状态
        o.setMemento();
    }
}
