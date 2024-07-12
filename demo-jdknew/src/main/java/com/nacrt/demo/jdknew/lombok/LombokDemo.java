package com.nacrt.demo.jdknew.lombok;

/**
 * @author mars
 * @description LombokDemo
 * @date 2024/7/11 10:30
 */
public class LombokDemo {
    public static void main(String[] args) {
        LombokUser alice = new LombokUser("alice", 10);
        String name = alice.getName();
    }
}
