package com.example.demo.mistakes.demo01;

import org.springframework.util.StopWatch;

import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.LongStream;

/**
 * @author zhenghao
 * @description StopWatch测试
 * @date 2020/6/9 16:47
 */
public class StopWatchDemo {

    public static void main(String[] args) {
        test1();
    }

    public static void test1() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        // do something
        stopWatch.stop();

        stopWatch.start("task1");
        task1();
        stopWatch.stop();
        stopWatch.start("task2");
        task2();
        stopWatch.stop();

        stopWatch.start("task3");
        task3();
        System.out.println(stopWatch.currentTaskName());    // 当前任务名称
        System.out.println(stopWatch.isRunning());          // 是否在运行
        stopWatch.stop();

        stopWatch.start("task4");
        task4();
        stopWatch.stop();
        System.out.println(stopWatch.prettyPrint());    // 任务执行情况表
        System.out.println(stopWatch.getLastTaskTimeNanos());   // 纳秒
        System.out.println(stopWatch.getTotalTimeMillis());     // 毫秒
        System.out.println(stopWatch.getTotalTimeSeconds());    // 秒
        System.out.println(stopWatch.getLastTaskName());    // 最后任务名称
        System.out.println(stopWatch.getLastTaskInfo().getTimeSeconds());   // 最后任务执行秒数
        System.out.println(stopWatch.getTaskCount());   // 任务总数
    }

    public static void task1() {
        long sum = LongStream.rangeClosed(1, 10000000).parallel().map(i -> ThreadLocalRandom.current().nextInt() * i).sum();
        System.out.println("task1 sum:" + sum);
    }

    public static void task2() {
        long sum = LongStream.rangeClosed(1, 8998800).parallel().map(i -> ThreadLocalRandom.current().nextInt() * i).sum();
        System.out.println("task2 sum:" + sum);
    }

    public static void task3() {
        long sum = LongStream.rangeClosed(1, 987654321L).parallel().map(i -> ThreadLocalRandom.current().nextInt() * i).sum();
        System.out.println("task2 sum:" + sum);
    }
    public static void task4() {
        long sum = LongStream.rangeClosed(1, 123456789).parallel().map(i -> ThreadLocalRandom.current().nextInt() * i).sum();
        System.out.println("task2 sum:" + sum);
    }

}
