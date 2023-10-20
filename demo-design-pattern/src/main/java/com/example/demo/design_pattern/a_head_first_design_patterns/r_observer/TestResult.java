package com.example.demo.design_pattern.a_head_first_design_patterns.r_observer;

import junit.framework.AssertionFailedError;
import junit.framework.Test;
import junit.framework.TestFailure;

import java.util.Enumeration;
import java.util.Vector;

// 来看下我们的目标角色，随便说下，由于 JUnit 功能的简单，只有一个目标——TestResult，因此 JUnit 只有一个具体目标角色。
//好长的代码，好像没有重点。去掉了大部分与主题无关的信息
//下面只列出了当 Failures 发生时是怎么来通知观察者的
public class TestResult extends Object {
    //这个是用来存放测试 Failures 的集合
    protected Vector fFailures;
    //这个就是用来存放注册进来的观察者的集合
    protected Vector fListeners;
    public TestResult() {
        fFailures= new Vector();
        fListeners= new Vector();
    }
    /**
     * Adds a failure to the list of failures. The passed in exception
     * caused the failure.
     */
    public synchronized void addFailure(Test test, AssertionFailedError t) {
        fFailures.addElement(new TestFailure(test, t));
        //下面就是通知各个观察者的 addFailure 方法
        for (Enumeration e = cloneListeners().elements(); e.hasMoreElements(); ) {
            ((TestListener)e.nextElement()).addFailure(test, t);
        }
    }
    /**
     * 注册一个观察者
     */
    public synchronized void addListener(TestListener listener) {
        fListeners.addElement(listener);
    }
    /**
     * 删除一个观察者
     */
    public synchronized void removeListener(TestListener listener) {
        fListeners.removeElement(listener);
    }
    /**
     * 返回一个观察者集合的拷贝，当然是为了防止对观察者集合的非法方式操作了
     * 可以看到所有使用观察者集合的地方都通过它
     */
    private synchronized Vector cloneListeners() {
        return (Vector)fListeners.clone();
    }
    // ……
}
