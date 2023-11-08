package com.example.demo.mistakes.demo03;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author zhenghao
 * @description ThreadPool案例
 * @date 2020/6/16 17:13
 */
@Component
@Slf4j
public class ThreadPoolDemo {
    public static void main(String[] args) throws InterruptedException {
//        new ThreadPoolDemo().oom1();
        System.out.println("===============================================结果：" + new ThreadPoolDemo().right());
    }

    public void oom1() throws InterruptedException {
//        ThreadPoolExecutor threadPool = (ThreadPoolExecutor) Executors.newFixedThreadPool(1);
        ThreadPoolExecutor threadPool = (ThreadPoolExecutor) Executors.newCachedThreadPool();
        //打印线程池的信息，稍后我会解释这段代码
        printStats(threadPool);
        for (int i = 0; i < 100000000; i++) {
            threadPool.execute(() -> {
                String payload = IntStream.rangeClosed(1, 1000000)
                        .mapToObj(__ -> "a")
                        .collect(Collectors.joining("")) + UUID.randomUUID().toString();
                try {
                    TimeUnit.HOURS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.info(payload);
            });
        }

        threadPool.shutdown();
        threadPool.awaitTermination(1, TimeUnit.HOURS);
    }

    private void printStats(ThreadPoolExecutor threadPool) {
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


    /**
     * 通过一些手段来改变这些默认工作行为，如：
     *  声明线程池后立即调用 prestartAllCoreThreads 方法，来启动所有核心线程；
     *  传入 true 给 allowCoreThreadTimeOut 方法，来让线程池在空闲的时候同样回收核心线程。
     */
    public int right() throws InterruptedException {
        //使用一个计数器跟踪完成的任务数
        AtomicInteger atomicInteger = new AtomicInteger();
        //创建一个具有2个核心线程、5个最大线程，使用容量为10的ArrayBlockingQueue阻塞队列作为工作队列的线程池，使用默认的AbortPolicy拒绝策略
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(
                2, 5,
                5, TimeUnit.SECONDS,
//                new ArrayBlockingQueue<>(10),
                new ArrayBlockingQueue<Runnable>(10) {  // 重写offer方法
                    @Override
                    public boolean offer(Runnable r) {
                        return false;
                    }
                },
                new ThreadFactoryBuilder().setNameFormat("demo-threadpool-%d").build(),
//                new ThreadPoolExecutor.AbortPolicy()
                (r, e) -> { // 自定义拒绝策略
                    if (!e.isShutdown()) {
                        try {
                            if (!e.getQueue().offer(r, 0, TimeUnit.SECONDS)) {
                                throw new RejectedExecutionException("Task " + r.toString() +
                                        " rejected from " +
                                        e.toString());
                            }
//                          e.getQueue().put(r);
                        } catch (InterruptedException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
        );

//        threadPool.prestartAllCoreThreads();
        printStats(threadPool);
        //每隔1秒提交一次，一共提交20次任务
        IntStream.rangeClosed(1, 20).forEach(i -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            int id = atomicInteger.incrementAndGet();
            try {
                threadPool.submit(() -> {
                    log.info("{} started", id);
                    //每个任务耗时10秒
                    try {
                        TimeUnit.SECONDS.sleep(10);
                    } catch (InterruptedException e) {
                    }
                    log.info("{} finished", id);
                });
            } catch (Exception ex) {
                //提交出现异常的话，打印出错信息并为计数器减一
                log.error("error submitting task {}", id, ex);
                atomicInteger.decrementAndGet();
            }
        });

        TimeUnit.SECONDS.sleep(60);
        threadPool.shutdownNow();
        return atomicInteger.intValue();
    }

    /**
     * 务必确认清楚线程池本身是不是复用的
     */
    public String demo3() throws InterruptedException {
//        ThreadPoolExecutor threadPool = (ThreadPoolExecutor) Executors.newCachedThreadPool();
        ThreadPoolExecutor threadPool = ThreadPoolUtil.getRightThreadPool();

        IntStream.rangeClosed(1, 10).forEach(i -> {
            threadPool.execute(() -> {
                // ...
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                }
            });
        });
        return "OK";
    }

}