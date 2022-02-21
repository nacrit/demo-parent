package com.acrt.example.demo_juc.util;

import java.util.concurrent.TimeUnit;

public class SleepUtils {

    /**
     * 休眠second秒
     *
     * @param second 秒
     */
    public synchronized static void sleep(long second) {
        try {
            TimeUnit.SECONDS.sleep(second);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 休眠second秒
     *
     * @param second 秒
     */
    public synchronized static void sleep(double second) {
        try {
            TimeUnit.MILLISECONDS.sleep((long) (second * 1000L));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
