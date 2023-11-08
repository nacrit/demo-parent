package com.example.demo.mistakes.demo03;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * @author zhenghao
 * @description 线程池工具类
 * @date 2020/6/28 16:54
 */
@Slf4j
public class ThreadPoolUtil {
    private static ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
            10, 50,
            2, TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(1000),
            new ThreadFactoryBuilder().setNameFormat("demo-threadpool-%d").build());
    public static ThreadPoolExecutor getRightThreadPool() {
        return threadPoolExecutor;
    }


    public static void printStats(ThreadPoolExecutor threadPool) {
        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.scheduleAtFixedRate(() -> {
            log.info("=========================");
            log.info("Pool Size: {}", threadPool.getPoolSize());
            log.info("Active Threads: {}", threadPool.getActiveCount());
            log.info("Number of Tasks Completed: {}", threadPool.getCompletedTaskCount());
            log.info("Number of Tasks in Queue: {}", threadPool.getQueue().size());
            log.info("Largest Pool Size: {}", threadPool.getLargestPoolSize());
            log.info("=====================================");

            if (threadPool.isShutdown()) {
                scheduledExecutorService.shutdownNow();
            }
        }, 0, 1, TimeUnit.SECONDS);
    }
}
