package com.example.demo.design_pattern.t_state;

// 状态（State）角色
public interface State {
    void doAction(Context context);
}

// 具体状态（Concrete State）角色
class StartState implements State {

    // 开始播放
    public void doAction(Context context) {
        System.out.println("Player is in start state");
        context.setState(this);
    }

    public String toString(){
        return "Start State";
    }
}

// 具体状态（Concrete State）角色
class StopState implements State {

    // 停止播放
    public void doAction(Context context) {
        System.out.println("Player is in stop state");
        context.setState(this);
    }

    public String toString(){
        return "Stop State";
    }
}