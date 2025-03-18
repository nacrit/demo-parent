package com.example.demo.design_pattern.a_head_first_design_patterns.composite;

import java.util.Enumeration;
import java.util.Vector;

//Test 接口——抽象构件角色
public interface Test {
    /**
     * Counts the number of test cases that will be run by this test.
     */
    int countTestCases();

    /**
     * Runs a test and collects its result in a TestResult instance.
     */
    void run(TestResult result);
}

//TestSuite 类的部分有关源码——Composite 角色，它实现了接口 Test
class TestSuite implements Test {
    //用了较老的 Vector 来保存添加的 test
    private Vector fTests = new Vector(10);
    private String fName;

    /**
     * Adds a test to the suite.
     */
    public void addTest(Test test) {
        //注意这里的参数是 Test 类型的。这就意味着 TestCase 和 TestSuite 以及以后实现 Test 接口的任何类都可以被添加进来
        fTests.addElement(test);
    }

    /**
     * Counts the number of test cases that will be run by this test.
     */
    public int countTestCases() {
        int count = 0;
        for (Enumeration e = tests(); e.hasMoreElements(); ) {
            Test test = (Test) e.nextElement();
            count = count + test.countTestCases();
        }
        return count;
    }

    private Enumeration tests() {
        return null;
    }

    /**
     * Runs the tests and collects their result in a TestResult.
     */
    public void run(TestResult result) {
        for (Enumeration e = tests(); e.hasMoreElements(); ) {
            if (result.shouldStop())
                break;
            Test test = (Test) e.nextElement();
            //关键在这个方法上面
            runTest(test, result);
        }
    }

    //这个方法里面就是递归的调用了，至于你的 Test 到底是什么类型的只有在运行的时候得知
    public void runTest(Test test, TestResult result) {
        test.run(result);
    }
}

//TestCase 的部分有关源码——Leaf 角色，你编写的测试类就是继承自它
abstract class TestCase extends Assert implements Test {
    /**
     * Counts the number of test cases executed by run(TestResult result).
     */
    public int countTestCases() {
        return 1;
    }

    /**
     * Runs the test case and collects the results in TestResult.
     */
    public void run(TestResult result) {
        result.run(this);
    }
}

class Assert extends junit.framework.TestCase {
}

class TestResult {
    public void run(TestCase testCase) {
        // .....
    }

    public boolean shouldStop() {
        return false;
    }
}