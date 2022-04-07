package com.acrt.example.demo_juc.a_thread;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * 1.线程与进程：一个进程之内可以分为一到多个线程，一个线程就是一个指令流，线程上下文切换成本一般上要比进程上下文切换低
 * 2.并发与并行：并发是一个CPU在不同的时间去不同线程中执行指令。并行是多个CPU同时处理不同的线程。
 *
 */
@Slf4j(topic = "c.CreateThreadDemo")
public class CreateThreadDemo {
    public static void main(String[] args) {
        create1().start();
        create2().start();
        create3AndStart();

    }

    /** 通过继承Thread类创建线程 */
    private static Thread create1() {
        // 此处使用匿名内部类，也相当于实现了Thread类
        return new Thread("t1"){
            @Override
            public void run() {
                // do something ...
                log.info("通过继承Thread类创建线程 .. ");
            }
        };
    }

    /** 通过实现Runnable接口创建线程 */
    private static Thread create2() {
        // 此处通过匿名内部类实现Runnable 来作Thread的构造方法的参数来实现线程的创建
        return new Thread(new Runnable() {
            @Override
            public void run() {
                log.info("通过实现Runnable接口创建线程 .. ");
            }
        }, "t2");
    }


    /** 通过FutureTask配合Thread创建线程，此线程可有返回值 */
    private static void create3AndStart() {
        FutureTask<String> futureTask = new FutureTask<>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                log.info("通过FutureTask及实现Callable接口来创建线程 .. ");
                return "有返回值的线程";
            }
        });
        new Thread(futureTask, "t3").start();

        try {
            // 此方法会等待线程执行完才执行
            log.info("create3 thread 返回值：{}", futureTask.get());
        } catch (Exception ignored) {}
    }
}
