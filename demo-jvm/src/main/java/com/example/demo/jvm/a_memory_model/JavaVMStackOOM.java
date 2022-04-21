package com.example.demo.jvm.a_memory_model;

/**
 * 代码清单2-6 创建线程导致内存溢出异常
 * VM Args：-Xss2M （这时候不妨设大些，请在32位系统下运行）
 * 注意 重点提示一下，如果读者要尝试运行上面这段代码，记得要先保存当前的工作，由于在Windows平台的虚拟机中，
 * Java的线程是映射到操作系统的内核线程上[1]，无限制地创建线程会对操作系统带来很大压力，
 * 上述代码执行时有很高的风险，可能会由于创建线程数量过多而导致操作系统假死。
 *
 * @author zhenghao
 * @date 2022/4/14 15:03
 */
public class JavaVMStackOOM {
    private void dontStop() {
        while (true) {
        }
    }
    public void stackLeakByThread() {
        while (true) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    dontStop();
                }
            });
            thread.start();
        }
    }
    public static void main(String[] args) throws Throwable {
        JavaVMStackOOM oom = new JavaVMStackOOM();
        oom.stackLeakByThread();
        /*
        结果：
            Exception in thread "main" java.lang.OutOfMemoryError: unable to create native thread
         */
    }
}