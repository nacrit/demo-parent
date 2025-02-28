package com.example.demo.design_pattern.b_hbwxz.b_adapter.adapter;

public interface Login3rdTarget {
    String loginByGitee(String code, String state);
    String loginByWechat(String... params);
    String loginByQQ(String... params);
}
