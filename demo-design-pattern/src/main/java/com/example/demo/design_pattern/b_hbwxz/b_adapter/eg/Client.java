package com.example.demo.design_pattern.b_hbwxz.b_adapter.eg;

/**
 * @author mars
 * @description Client
 * @date 2023/11/11 23:54
 */
public class Client {
    public static void main(String[] args) {
        Target t = new Adapter(new Adaptee());
        t.request();
    }
}
