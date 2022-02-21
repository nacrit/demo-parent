package com.example.demo.design_pattern.r_observer;

import junit.framework.AssertionFailedError;
import junit.framework.Test;

import java.io.PrintStream;

// 具体观察者角色，我们采用最简单的 TextUI 下的情况来说明（AWT，Swing 对于整天做 Web 应用的人来说，已经很陌生了）
public class ResultPrinter implements TestListener {
    PrintStream fWriter;
    int fColumn = 0;
    public PrintStream getWriter() {
        return fWriter;
    }
    //省略好多啊，主要是显示代码
    // ……

    //下面就是实现接口 TestListener 的四个方法
    //填充方法的行为很简单的说
    /**
     * @see junit.framework.TestListener#addError(Test, Throwable)
     */
    public void addError(Test test, Throwable t) {
        getWriter().print("E");
    }
    /**
     * @see junit.framework.TestListener#addFailure(Test, AssertionFailedError)
     */
    public void addFailure(Test test, AssertionFailedError t) {
        getWriter().print("F");
    }
    /**
     * @see junit.framework.TestListener#endTest(Test)
     */
    public void endTest(Test test) {
    }
    /**
     * @see junit.framework.TestListener#startTest(Test)
     */
    public void startTest(Test test) {
        getWriter().print(".");
        if (fColumn++ >= 40) {
            getWriter().println();
            fColumn= 0;
        }
    }
}
