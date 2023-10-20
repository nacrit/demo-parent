package com.example.demo.design_pattern.a_hfdp.s_strategy;

// 抽象策略
public interface Strategy {
    int doOperation(int num1, int num2);
}

// 具体策略 +
class OperationAdd implements Strategy{
    @Override
    public int doOperation(int num1, int num2) {
        return num1 + num2;
    }
}
// 具体策略 -
class OperationSubtract implements Strategy{
    @Override
    public int doOperation(int num1, int num2) {
        return num1 - num2;
    }
}
// 具体策略 *
class OperationMultiply implements Strategy{
    @Override
    public int doOperation(int num1, int num2) {
        return num1 * num2;
    }
}