package com.example.demo.design_pattern.a_head_first_design_patterns.v_visitor;

// The Flower hierarchy cannot be changed:
//元素角色
interface Flower {
    // 接收访问者
    void accept(Visitor v);
}

//以下三个具体元素角色 -- 三种花
// 唐菖蒲
class Gladiolus implements Flower {
    public void accept(Visitor v) { v.visit(this);}
}
// Runuculus
class Runuculus implements Flower {
    public void accept(Visitor v) { v.visit(this);}
}
// 菊花
class Chrysanthemum implements Flower {
    public void accept(Visitor v) { v.visit(this);}
}