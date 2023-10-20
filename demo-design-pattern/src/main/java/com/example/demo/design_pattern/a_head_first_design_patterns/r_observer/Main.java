package com.example.demo.design_pattern.a_head_first_design_patterns.r_observer;

import junit.framework.TestResult;
import junit.textui.ResultPrinter;

public class Main {
    public static void main(String[] args) {
        ResultPrinter fPrinter = new ResultPrinter(System.out);
        //就是在这里注册的
        new TestResult().addListener(fPrinter);

        new com.example.demo.design_pattern.a_head_first_design_patterns.r_observer.TestResult().addListener(new com.example.demo.design_pattern.a_head_first_design_patterns.r_observer.ResultPrinter());
    }
}
