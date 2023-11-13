package com.example.demo.design_pattern.b_hbwxz.b_adapter.eg;

/**
 * @author mars
 * @description Adapter
 * @date 2023/11/11 23:54
 */
public class Adapter implements Target{
    private final Adaptee adaptee;

    public Adapter(Adaptee adaptee) {
        this.adaptee = adaptee;
    }

    public void request() {
        adaptee.speecificRequest();
    }
}
