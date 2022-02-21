package com.example.demo.design_pattern.t_state;

//使用环境（Context）角色
public class Context {
    private State state;
    public Context(){
        state = null;
    }
    public void setState(State state){
        this.state = state;
    }
    public State getState(){
        return state;
    }
}
