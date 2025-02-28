package com.acrt.example.demo_juc.a_thread;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.LockSupport;

import static com.acrt.example.demo_juc.util.SleepUtils.sleep;

/**
 * 打断方法测试
 */
@Slf4j(topic = "c.InterruptDemo")
public class InterruptDemo {

    public static void main(String[] args) {
//        testSleep();
        testRunnable();
//        testPark();
    }

    /**
     * 打断 sleep 的线程, 会清空打断状态
     */
    private static void testSleep() {
        Thread t1 = new Thread(() -> {
            sleep(1);
        }, "t1");
        t1.start();
        sleep(0.5);
        t1.interrupt();
        log.debug(" 打断状态: {}", t1.isInterrupted());
    }

    /**
     * 打断正常运行的线程, 不会清空打断状态
     */
    private static void testRunnable() {
        Thread t2 = new Thread(() -> {
            while (true) {
                Thread current = Thread.currentThread();
                boolean interrupted = current.isInterrupted();
                log.debug(" 打断状态: {}, status={}", interrupted, current.getState());
                if (interrupted) {
                    break;
                }
            }
        }, "t2");
        t2.start();
        sleep(0.1);
        t2.interrupt();
        sleep(0.1);
        log.debug(" 打断状态: {}, status={}", t2.isInterrupted(), t2.getState());
    }

    /**
     * 打断 park 线程, 不会清空打断状态
     * 如果打断标记已经是 true, 则 park 会失效
     */
    private static void testPark() {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 3; i++) {
                log.debug("1. park..  打断状态: {}", Thread.currentThread().isInterrupted());
                // 线程暂停
                LockSupport.park(); // 暂停线程执行，打断之后再执行park会失效（不再暂停），可以使用Thread.interrupted()清除打断状态
                // 线程恢复后执行
                log.debug("2. unpark.. 打断状态：{}", Thread.currentThread().isInterrupted());

                if (i == 1) {
                    Thread.interrupted();// 清楚打断状态，之后的park有效
                    log.debug("3. 清除打断标记 打断状态：{}", Thread.currentThread().isInterrupted());
                }
            }
        }, "t1");
        t1.start();
        sleep(1);
        log.debug("4. 开始打断线程 ..");
        t1.interrupt(); // 被打断的线程，park会失效，之后也无法再park，可以使用Thread.interrupted()清除打断状态，之后可再park
        sleep(1);
        log.debug("5. 开始 unpark 线程 ..");
        LockSupport.unpark(t1);
    }

}
