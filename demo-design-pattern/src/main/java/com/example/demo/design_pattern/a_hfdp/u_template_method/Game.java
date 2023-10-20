package com.example.demo.design_pattern.a_hfdp.u_template_method;

// 抽象类
public abstract class Game {
    abstract void initialize();
    abstract void startPlay();
    abstract void endPlay();
    //模板
    public final void play(){
        //初始化游戏
        initialize();
        //开始游戏
        startPlay();
        //结束游戏
        endPlay();
    }
}

// 具体类
class Cricket extends Game {
    @Override
    void endPlay() {
        System.out.println("Cricket Game Finished!");
    }
    @Override
    void initialize() {
        System.out.println("Cricket Game Initialized! Start playing.");
    }
    @Override
    void startPlay() {
        System.out.println("Cricket Game Started. Enjoy the game!");
    }
}

// 具体类 足球
class Football extends Game {
    @Override
    void endPlay() {
        System.out.println("Football Game Finished!");
    }
    @Override
    void initialize() {
        System.out.println("Football Game Initialized! Start playing.");
    }
    @Override
    void startPlay() {
        System.out.println("Football Game Started. Enjoy the game!");
    }
}