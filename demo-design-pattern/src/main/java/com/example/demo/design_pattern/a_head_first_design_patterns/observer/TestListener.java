package com.example.demo.design_pattern.a_head_first_design_patterns.observer;

import junit.framework.AssertionFailedError;
import junit.framework.Test;

//下面是我们的抽象观察者角色，JUnit 是采用接口来实现的，这也是一般采用的方式。
//可以看到这里面定义了四个不同的 update 方法，对应四种不同的状态变化
public interface TestListener {
    /**
     * An error occurred.
     */
    public void addError(Test test, Throwable t);

    /**
     * A failure occurred.
     */
    public void addFailure(Test test, AssertionFailedError t);

    /**
     * A test ended.
     */
    public void endTest(Test test);

    /**
     * A test started.
     */
    public void startTest(Test test);
}
