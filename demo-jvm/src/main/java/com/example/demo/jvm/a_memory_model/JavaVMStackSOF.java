package com.example.demo.jvm.a_memory_model;

/**
 * 代码清单2-4 虚拟机栈和本地方法栈测试
 * 如果线程请求的栈深度大于虚拟机所允许的最大深度，将抛出StackOverflowError异常。
 * VM Args：-Xss128k
 *
 * @author zhenghao
 * @date 2022/4/14 14:55
 */
public class JavaVMStackSOF {
    private int stackLength = 1;
    public void stackLeak() {
        stackLength++;
        stackLeak();
    }
    public static void main(String[] args) throws Throwable {
        JavaVMStackSOF oom = new JavaVMStackSOF();
        try {
            oom.stackLeak();
        } catch (Throwable e) {
            System.out.println("stack length:" + oom.stackLength);
            throw e;
        }
        /*
        stack length:1085
        Exception in thread "main" java.lang.StackOverflowError
            at com.example.demo.jvm.a_memory_model.JavaVMStackSOF.stackLeak(JavaVMStackSOF.java:15)
            at com.example.demo.jvm.a_memory_model.JavaVMStackSOF.stackLeak(JavaVMStackSOF.java:15)
         */
    }
}
