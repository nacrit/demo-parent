package com.example.demo.design_pattern.a_hfdp.s_strategy;

// 算法使用环境(Context)角色
public class Context {
    private Strategy strategy;

    public Context(Strategy strategy){
        this.strategy = strategy;
    }

    public int executeStrategy(int num1, int num2){
        return strategy.doOperation(num1, num2);
    }
}