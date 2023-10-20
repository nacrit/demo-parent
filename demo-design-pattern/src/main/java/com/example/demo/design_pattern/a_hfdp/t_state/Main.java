package com.example.demo.design_pattern.a_hfdp.t_state;

public class Main {
    public static void main(String[] args) {
        // 使用环境
        Context context = new Context();

        // 开始
        StartState startState = new StartState();
        startState.doAction(context);
        System.out.println(context.getState().toString());

        // 停止
        StopState stopState = new StopState();
        stopState.doAction(context);
        System.out.println(context.getState().toString());
    }
}
