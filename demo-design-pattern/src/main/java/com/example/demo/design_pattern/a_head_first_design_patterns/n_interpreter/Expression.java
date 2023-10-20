package com.example.demo.design_pattern.a_head_first_design_patterns.n_interpreter;

//抽象表达式角色，也可以用接口来实现
public abstract class Expression {
    public abstract int interpret(Context con);
}

//终结符表达式角色 不可变
class Constant extends Expression {
    private int i;

    public Constant(int i) {
        this.i = i;
    }

    public int interpret(Context con) {
        return i;
    }
}
// 可变
class Variable extends Expression {
    public int interpret(Context con) {
        //this 为调用 interpret 方法的 Variable 对象
        return con.LookupValue(this);
    }
}

//非终结符表达式角色 +
class Add extends Expression {
    private Expression left, right;

    public Add(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    public int interpret(Context con) {
        return left.interpret(con) + right.interpret(con);
    }
}
// -
class Subtract extends Expression {
    private Expression left, right;

    public Subtract(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    public int interpret(Context con) {
        return left.interpret(con) - right.interpret(con);
    }
}
// *
class Multiply extends Expression {
    private Expression left, right;

    public Multiply(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    public int interpret(Context con) {
        return left.interpret(con) * right.interpret(con);
    }
}
// /
class Division extends Expression {
    private Expression left, right;

    public Division(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    public int interpret(Context con) {
        try {
            return left.interpret(con) / right.interpret(con);
        } catch (ArithmeticException ae) {
            System.out.println("被除数为 0！");
            return -11111;
        }
    }
}