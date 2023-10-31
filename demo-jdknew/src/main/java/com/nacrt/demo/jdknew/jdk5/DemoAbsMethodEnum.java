package com.nacrt.demo.jdknew.jdk5;

/**
 * @author mars
 * @description DemoEnum
 * @date 2023/10/30 14:11
 */
enum DemoAbsMethodEnum {
    Hello {
        @Override
        public void print() {
            System.out.println("hello !!");
        }
    },
    ;
    public abstract void print();
}
