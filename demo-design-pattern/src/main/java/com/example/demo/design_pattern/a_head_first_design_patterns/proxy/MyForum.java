package com.example.demo.design_pattern.a_head_first_design_patterns.proxy;

// 抽象主题角色 MyForum，里面定义了真实主题和代理主题的共同接口——发帖功能。
public interface MyForum {
    // 发帖功能
    void AddFile();
}

// 真实主题角色
class RealMyForum implements MyForum {
    @Override
    public void AddFile() {
        System.out.println("send post");
    }
}

// 代理主题角色
class MyForumProxy implements MyForum {
    private static final int ASSOCIATOR = 100;

    private RealMyForum forum = new RealMyForum() ;
    private int permission; //权限值
    public MyForumProxy(int permission) {
        this.permission = permission ;
    }
    //实现的接口
    public void AddFile() {
        //满足权限设置的时候才能够执行操作
        //Constants 是一个常量类
        if(ASSOCIATOR == permission) {
            forum.AddFile();
        } else {
            System.out.println("You are not a associator of MyForum, please registe!");
        }
    }
}
