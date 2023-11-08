package com.example.demo.mistakes.demo31;

import java.time.LocalDateTime;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

/**
 * @author zhenghao
 * @description 并行流demo
 * @date 2020/6/18 16:31
 */
public class ParallelDemo {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        demo1();
//        final ParallelDemo parallelDemo = new ParallelDemo();
//        StopWatch stopWatch = new StopWatch();
//        stopWatch.start("thread");
//        System.out.println("thread:" + parallelDemo.thread(10000, 20));
//        stopWatch.stop();
//        stopWatch.start("threadPool");
//        System.out.println("threadPool:" + parallelDemo.threadPool(10000, 20));
//        stopWatch.stop();
//        stopWatch.start("forkJoin");
//        System.out.println("forkJoin:" + parallelDemo.forkJoin(10000, 20));
//        stopWatch.stop();
//        stopWatch.start("stream");
//        System.out.println("stream:" + parallelDemo.stream(10000, 20));
//        stopWatch.stop();
//        stopWatch.start("completableFuture");
//        System.out.println("completableFuture:" + parallelDemo.completableFuture(10000, 20));
//        stopWatch.stop();
//        System.out.println(stopWatch.prettyPrint());
    }

    // 并行流的功能
    public static void demo1() {
        IntStream.rangeClosed(1, 10).parallel()
                .forEach(i -> {
                    for (int j = 100; j < 110; j++) {
                        System.out.println(LocalDateTime.now() + ",i=" + i + "-------j=" + j);
                        try {
                            TimeUnit.SECONDS.sleep(1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    /**
     * 用 20 个线程（threadCount）以并行方式总计执行 10000 次（taskCount）操作。
     * 因为单个任务单线程执行需要 10 毫秒（任务代码如下），也就是每秒吞吐量是 100 个操作，
     * 那 20 个线程 QPS 是 2000，执行完 10000 次操作最少耗时 5 秒。
     */
    public static void demo2() {

    }

    private void increment(AtomicInteger atomicInteger) {
        atomicInteger.incrementAndGet();
        try {
            TimeUnit.MILLISECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    // 测试一下这五种方式，是否都可以利用更多的线程并行执行操作。

    /**
     *  第一种方式是使用线程。直接把任务按照线程数均匀分割，分配到不同的线程执行，
     *  使用 CountDownLatch 来阻塞主线程，直到所有线程都完成操作。这种方式，需要我们自己分割任务：
     * @param taskCount 任务数
     * @param threadCount 线程数
     * @return 结果
     * @throws InterruptedException
     */
    private int thread(int taskCount, int threadCount) throws InterruptedException {
        //总操作次数计数器
        AtomicInteger atomicInteger = new AtomicInteger();
        //使用CountDownLatch来等待所有线程执行完成
        CountDownLatch countDownLatch = new CountDownLatch(threadCount);
        //使用IntStream把数字直接转为Thread
        IntStream.rangeClosed(1, threadCount).mapToObj(i -> new Thread(() -> {
            //手动把taskCount分成taskCount份，每一份有一个线程执行
            IntStream.rangeClosed(1, taskCount / threadCount).forEach(j -> increment(atomicInteger));
            //每一个线程处理完成自己那部分数据之后，countDown一次
            countDownLatch.countDown();
        })).forEach(Thread::start);
        //等到所有线程执行完成
        countDownLatch.await();
        //查询计数器当前值
        return atomicInteger.get();
    }


    /**
     * 第二种方式是，使用 Executors.newFixedThreadPool 来获得固定线程数的线程池，
     * 使用 execute 提交所有任务到线程池执行，最后关闭线程池等待所有任务执行完成：
     * @param taskCount 任务数
     * @param threadCount 线程数
     * @return 结果
     * @throws InterruptedException
     */
    private int threadPool(int taskCount, int threadCount) throws InterruptedException {
        //总操作次数计数器
        AtomicInteger atomicInteger = new AtomicInteger();
        //初始化一个线程数量=threadCount的线程池
        ExecutorService executorService = Executors.newFixedThreadPool(threadCount);
        //所有任务直接提交到线程池处理
        IntStream.rangeClosed(1, taskCount).forEach(i -> executorService.execute(() -> increment(atomicInteger)));
        //提交关闭线程池申请，等待之前所有任务执行完成
        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.HOURS);
        //查询计数器当前值
        return atomicInteger.get();
    }


    /**
     * 第三种方式是，使用 ForkJoinPool 而不是普通线程池执行任务。ForkJoinPool 和传统的 ThreadPoolExecutor 区别在于，
     * 前者对于 n 并行度有 n 个独立队列，后者是共享队列。如果有大量执行耗时比较短的任务，ThreadPoolExecutor 的单队列就可能会成为瓶颈。
     * 这时，使用 ForkJoinPool 性能会更好。因此，ForkJoinPool 更适合大任务分割成许多小任务并行执行的场景，
     * 而 ThreadPoolExecutor 适合许多独立任务并发执行的场景。在这里，我们先自定义一个具有指定并行数的 ForkJoinPool，
     * 再通过这个 ForkJoinPool 并行执行操作：
     */
    private int forkJoin(int taskCount, int threadCount) throws InterruptedException {
        //总操作次数计数器
        AtomicInteger atomicInteger = new AtomicInteger();
        //自定义一个并行度=threadCount的ForkJoinPool
        ForkJoinPool forkJoinPool = new ForkJoinPool(threadCount);
        //所有任务直接提交到线程池处理
        forkJoinPool.execute(() -> IntStream.rangeClosed(1, taskCount).parallel().forEach(i -> increment(atomicInteger)));
        //提交关闭线程池申请，等待之前所有任务执行完成
        forkJoinPool.shutdown();
        forkJoinPool.awaitTermination(1, TimeUnit.HOURS);
        //查询计数器当前值
        return atomicInteger.get();
    }

    /**
     * 第四种方式是，直接使用并行流，并行流使用公共的 ForkJoinPool，也就是 ForkJoinPool.commonPool()。
     * 公共的 ForkJoinPool 默认的并行度是 CPU 核心数 -1，原因是对于 CPU 绑定的任务分配超过 CPU 个数的线程没有意义。
     * 由于并行流还会使用主线程执行任务，也会占用一个 CPU 核心，所以公共 ForkJoinPool 的并行度即使 -1 也能用满所有 CPU 核心。
     * 这里，我们通过配置强制指定（增大）了并行数，但因为使用的是公共 ForkJoinPool，
     * 所以可能会存在干扰，你可以回顾下第 3 讲有关线程池混用产生的问题：
     */
    private int stream(int taskCount, int threadCount) {
        //设置公共ForkJoinPool的并行度
        System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", String.valueOf(threadCount));
        //总操作次数计数器
        AtomicInteger atomicInteger = new AtomicInteger();
        //由于我们设置了公共ForkJoinPool的并行度，直接使用parallel提交任务即可
        IntStream.rangeClosed(1, taskCount).parallel().forEach(i -> increment(atomicInteger));
        //查询计数器当前值
        return atomicInteger.get();
    }

    /**
     * 第五种方式是，使用 CompletableFuture 来实现。
     * CompletableFuture.runAsync 方法可以指定一个线程池，一般会在使用 CompletableFuture 的时候用到：
     */
    private int completableFuture(int taskCount, int threadCount) throws InterruptedException, ExecutionException {
        //总操作次数计数器
        AtomicInteger atomicInteger = new AtomicInteger();
        //自定义一个并行度=threadCount的ForkJoinPool
        ForkJoinPool forkJoinPool = new ForkJoinPool(threadCount);
        //使用CompletableFuture.runAsync通过指定线程池异步执行任务
        CompletableFuture.runAsync(() -> IntStream.rangeClosed(1, taskCount).parallel().forEach(i -> increment(atomicInteger)), forkJoinPool).get();
        //查询计数器当前值
        return atomicInteger.get();
    }

}
