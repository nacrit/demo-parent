package com.example.demo.design_pattern.a_head_first_design_patterns.q_memento;


//备忘录管理者角色
public class Caretaker {
    private MementoIF m ;
    public void saveMemento(MementoIF m){
        this.m = m;
    }
    public MementoIF getMemento(){
        return m;
    }
}
