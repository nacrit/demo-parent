package com.acrt.example.demo_juc.thread;

import lombok.extern.slf4j.Slf4j;

import static com.acrt.example.demo_juc.util.SleepUtils.sleep;

@Slf4j(topic = "c.DaemonDemo")
public class DaemonDemo {
    /**
     * 设置t1为main的守护线程，main结束t1也会结束
     */
    public static void main(String[] args) {
        log.debug("开始运行...");
        Thread t1 = new Thread(() -> {
            log.debug("开始运行...");
            sleep(2);
            log.debug("运行结束..."); // 1秒后main结束，daemon也结束，此日志不会打印
        }, "daemon");
        // 设置该线程为守护线程
        t1.setDaemon(true);
        t1.start();
        sleep(1);
        log.debug("运行结束...");
    }
}
