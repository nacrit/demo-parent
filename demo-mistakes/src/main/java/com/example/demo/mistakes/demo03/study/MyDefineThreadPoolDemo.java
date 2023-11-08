package com.example.demo.mistakes.demo03.study;

import java.util.concurrent.*;

;

/**
 * @author zhenghao
 * @description 自定义线程池demo
 * @date 2020/6/28 11:23
 */
public class MyDefineThreadPoolDemo {
    public static void main(String[] args) throws InterruptedException {
//        demo1();
//        demo2();
        demo3();
    }

    public static void demo1() {
        /*
        * public ThreadPoolExecutor(
              int corePoolSize,   // 规定线程池有几个线程(worker)在运行
              int maximumPoolSize,  // 当workQueue满了,不能添加任务的时候，这个参数才会生效。规定线程池最多只能有多少个线程(worker)在执行
              long keepAliveTime,   // 超出corePoolSize大小的那些线程的生存时间,这些线程如果长时间没有执行任务并且超过了keepAliveTime设定的时间，就会消亡
              TimeUnit unit,        // 生存时间对于的单位
              BlockingQueue<Runnable> workQueue,    // 存放任务的队列
              ThreadFactory threadFactory,  // 创建线程的工厂
              RejectedExecutionHandler handler // 当workQueue满，且池线程数量=maximumPoolSize，将执行拒绝策略
          )
          存放线程的队列：
            SynchronousQueue 是无界的，是一种无缓冲的等待队列
          拒绝策略：
            ThreadPoolExecutor.AbortPolicy	        丢弃任务并抛出RejectedExecutionException异常。
            ThreadPoolExecutor.DiscardPolicy	    丢弃任务，但是不抛出异常。
            ThreadPoolExecutor.DiscardOldestPolicy	丢弃队列最前面的任务，然后重新尝试执行任务（重复此过程）
            ThreadPoolExecutor.CallerRunsPolicy	    由调用线程处理该任务
        *
        * */
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                5,  // 核心线程数
                10, // 最大线程数
                0L, // 线程空闲存活时间
                TimeUnit.SECONDS,   // 存活时间单位
                new LinkedBlockingDeque<>(8)    // 线程队列
        );
        printStats(threadPoolExecutor);
        for (int i = 0; i < 1000; i++) {
            threadPoolExecutor.execute(() -> {
//                System.out.println("线程开始执行....");
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("线程结束执行....");
            });
            try {
                TimeUnit.MILLISECONDS.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        threadPoolExecutor.shutdown();
    }

    /**
     * SynchronousQueue是一种阻塞队列，其中每个 put 必须等待一个 take，反之亦然。
     * 它是线程安全的，是阻塞的。
     * 不允许使用 null 元素。
     * 公平排序策略是指调用put的线程之间，或take的线程之间。
     * @throws InterruptedException 中断异常
     */
    private static void demo2() throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        SynchronousQueue<String> sq = new SynchronousQueue<>();
        queuePut(executorService, sq, "hello");
        queuePut(executorService, sq, "world");
        queueTake(executorService, sq);
        queueTake(executorService, sq);
        queueTake(executorService, sq);
        queuePut(executorService, sq, "java");
        executorService.shutdown();
        if (!executorService.awaitTermination(10, TimeUnit.SECONDS)) {
            System.out.println("线程未结束");
            executorService.shutdownNow();
        }
    }

    /**
     *  LinkedBlockingQueue 这个队列排列元素FIFO（先进先出），是一个无界缓存的基于链表的等待队列，读写锁分离
     *  ArrayBlockingQueue 是有界的，是一个有界缓存的基于数组的等待队列，读写同锁
     */
    public static void demo3() throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        LinkedBlockingQueue<String> lq = new LinkedBlockingQueue<>();
        queuePut(executorService, lq, "hello");
        queuePut(executorService, lq, "world");
        queuePut(executorService, lq, "java");
        queueTake(executorService, lq);
        queueTake(executorService, lq);
        queueTake(executorService, lq);
        System.out.println("-----------------------------------");
        ArrayBlockingQueue<String> aq = new ArrayBlockingQueue<>(3);
        queuePut(executorService, aq, "hello");
        queuePut(executorService, aq, "world");
        queuePut(executorService, aq, "java");
        queueTake(executorService, aq);
        queueTake(executorService, aq);
        queueTake(executorService, aq);
        executorService.shutdown();
        if (!executorService.awaitTermination(10, TimeUnit.SECONDS)) {
            System.out.println("线程未结束");
            executorService.shutdownNow();
        }
    }

    private static void queueTake(ExecutorService executorService, BlockingQueue<String> sq) {
        executorService.execute(() -> {
            try {
                System.out.println(sq.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    private static void queuePut(ExecutorService executorService, BlockingQueue<String> sq, String msg) {
        executorService.execute(() -> {
            try {
                if ("hello".equals(msg)) {
                    TimeUnit.SECONDS.sleep(3);
                }
                System.out.println("put " + msg);
                sq.put(msg);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }


    private static void printStats(ThreadPoolExecutor threadPool) {
        Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(() -> {
            System.out.println("=========================");
            System.out.println("Pool Size: " + threadPool.getPoolSize());
            System.out.println("Active Threads: " + threadPool.getActiveCount());
            System.out.println("Number of Tasks Completed: " + threadPool.getCompletedTaskCount());
            System.out.println("Number of Tasks in Queue: " + threadPool.getQueue().size());
            System.out.println("Largest Pool Size: " + threadPool.getLargestPoolSize());
            System.out.println("=====================================");
        }, 0, 1, TimeUnit.SECONDS);
    }
}
