package com.example.demo.design_pattern.a_head_first_design_patterns.k_proxy;

public class Main {
    public static void main(String[] args) {
        // 游客
        MyForumProxy mfp1 = new MyForumProxy(1);
        mfp1.AddFile();

        // 注册的用户
        MyForumProxy mfp2 = new MyForumProxy(100);
        mfp2.AddFile();
    }
}
