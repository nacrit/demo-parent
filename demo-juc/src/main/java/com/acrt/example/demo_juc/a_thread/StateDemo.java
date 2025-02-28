package com.acrt.example.demo_juc.a_thread;

import lombok.extern.slf4j.Slf4j;

import static com.acrt.example.demo_juc.util.SleepUtils.sleep;

@Slf4j(topic = "c.StateDemo")
public class StateDemo {
    /**
     * 线程的6种状态示例
     */
    public static void main(String[] args) {

        // NEW 新创建线程
        Thread t1 = new Thread(() -> {
            log.debug("t1 running .. ");
        }, "t1");

        // RUNNABLE 正在运行的线程
        Thread t2 = new Thread(() -> {
            log.debug("t2 running .. ");
            for (; ; ) {
            }
//            log.debug("t2 completed .. ");
        }, "t2");
        t2.start();

        // TERMINATED 已完成的线程
        Thread t3 = new Thread(() -> {
            log.debug("t3 running .. ");
            log.debug("t3 completed .. ");
        }, "t3");
        t3.start();

        // TIMED_WAITING sleep的线程（限时等待）
        Thread t4 = new Thread(() -> {
            synchronized (StateDemo.class) {
                log.debug("t4 running .. ");
                sleep(5L);
                log.debug("t4 completed .. ");
            }
        }, "t4");
        t4.start();

        // WAITING 等待别的线程的线程
        Thread t5 = new Thread(() -> {
            try {
                log.debug("t5 running .. ");
                t2.join(); // 等待t2线程执行完
                log.debug("t5 completed .. ");
            } catch (InterruptedException e) {
                log.warn("t5 interrupted .. ", e);
            }
        }, "t5");
        t5.start();

        // BLOCKED 同步锁阻塞的线程
        Thread t6 = new Thread(() -> {
            log.debug("t6 running 1 .. ");
            sleep(0.1);
            synchronized (StateDemo.class) {
                log.debug("t6 running 2 .. ");
            }
            log.debug("t6 completed .. ");
        }, "t6");
        t6.start();

        sleep(1L);
        log.debug("t1 state:{}", t1.getState());
        log.debug("t2 state:{}", t2.getState());
        log.debug("t3 state:{}", t3.getState());
        log.debug("t4 state:{}", t4.getState());
        log.debug("t5 state:{}", t5.getState());
        log.debug("t6 state:{}", t6.getState());
    }

}
