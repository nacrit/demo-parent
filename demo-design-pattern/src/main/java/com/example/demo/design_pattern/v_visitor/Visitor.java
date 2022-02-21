package com.example.demo.design_pattern.v_visitor;

//访问者角色
interface Visitor {
    // 访问
    void visit(Gladiolus g);
    void visit(Runuculus r);
    void visit(Chrysanthemum c);
}

// Add the ability to produce a string:
//实现的具体访问者角色
class StringVal implements Visitor {
    String s;
    public String toString() { return s; }
    public void visit(Gladiolus g) {
        s = "Gladiolus";
    }
    public void visit(Runuculus r) {
        s = "Runuculus";
    }
    public void visit(Chrysanthemum c) {
        s = "Chrysanthemum";
    }
}
// Add the ability to do "Bee" activities:
//另一个具体访问者角色
class Bee implements Visitor {
    public void visit(Gladiolus g) {
        System.out.println("Bee and Gladiolus");
    }
    public void visit(Runuculus r) {
        System.out.println("Bee and Runuculus");
    }
    public void visit(Chrysanthemum c) {
        System.out.println("Bee and Chrysanthemum");
    }
}