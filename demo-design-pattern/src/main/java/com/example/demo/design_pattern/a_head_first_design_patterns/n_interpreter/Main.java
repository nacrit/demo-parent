package com.example.demo.design_pattern.a_head_first_design_patterns.n_interpreter;

// 客户角色 测试程序，计算 (a*b)/(a-b+2)
public class Main {
    // 抽象表达式角色
    private static Expression ex;
    // 上下文
    private static Context con;
    public static void main(String[] args) {
        con = new Context();
        //设置变量、常量
        Variable a = new Variable();
        Variable b = new Variable();
        Constant c = new Constant(2);
        //为变量赋值
        con.addValue(a, 5);
        con.addValue(b, 6);
        //运算，对句子的结构由我们自己来分析，构造 (a*b)/(a-b+2)=(5*6)/(5-6+2)
        ex = new Division(new Multiply(a, b), new Add(new Subtract(a, b), c));
        // 调用解释操作，得出结果
        System.out.println("运算结果为：" + ex.interpret(con));
    }
}
