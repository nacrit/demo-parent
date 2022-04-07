package com.acrt.example.demo_juc.util;

import java.util.concurrent.TimeUnit;

public class SleepUtils {

    /**
     * 休眠second秒
     *
     * @param milliSecond 秒
     */
    private synchronized static void sleep(long milliSecond) {
        try {
            TimeUnit.MILLISECONDS.sleep(milliSecond);
        } catch (InterruptedException e) {
            // 恢复打断标记
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
    }

    /**
     * 休眠second秒
     *
     * @param second 秒
     */
    public synchronized static void sleep(double second) {
        sleep((long) (second * 1_000L));
    }
}
