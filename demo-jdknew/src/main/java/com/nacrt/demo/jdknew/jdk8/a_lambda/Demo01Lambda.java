package com.nacrt.demo.jdknew.jdk8.a_lambda;


import java.util.Comparator;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.*;

/**
 * Lambda案例
 */
public class Demo01Lambda {
    public static void main(String[] args) {
        // 初体验
        //demo1();

        // 各种类型
        //demo2();

        // 简单demo
        demo3();

    }

    private static void demo3() {
        // test1
        System.out.println(retry(() -> 1 / 0, 3));
        // test2
        System.out.println(retry(() -> 1, 3));
    }

    /**
     * 重试方法
     */
    public static <T> T retry(Supplier<T> supplier, int retryCount) {
        for (int i = 0; i <= retryCount; i++) {
            try {
                return supplier.get();
            } catch (Exception e) {
                if (i < retryCount) {
                    System.out.println("代码执行异常, 准备重试 .., e=" + e.toString());
                }
            }
        }
        return null;
    }


    // lambda各种语法格式
    private static void demo2() {
        // 1.无参无返回值
        Runnable task = () -> System.out.println("hello");
        task.run();

        // 2.有参数，无返回值（消费型）
        Consumer<Integer> consumer = integer -> System.out.println("integer=" + integer); // 一个参数
        consumer.accept(222);
        BiConsumer<Integer, Integer> biConsumer = (integer, integer2) -> System.out.println("biConsumer:" + integer + "," + integer2); // 两个参数
        biConsumer.accept(21, 22);
        // DoubleConsumer

        // 3.无参有返回值的（供给型）
        Supplier<String> supplier = () -> "supplier result"; // 自定义返回值类型
        System.out.println(supplier.get());
        BooleanSupplier boSup = () -> ThreadLocalRandom.current().nextBoolean();
        System.out.println(boSup.getAsBoolean()); // boolean类型返回值


        // 4.有参有返回值的（函数型）
        Function<Integer, String> castFun = String::valueOf; // 自定义参数类型和返回类型
        System.out.println(castFun.apply(444));
        BiFunction<Integer, Integer, Integer> addFun = Integer::sum; // 两个参数
        System.out.println(addFun.apply(41, 42));
        // DoubleBinaryOperator（两个double参数，一个double返回值）

        // 判断型（断定型）
        Predicate<Integer> predicate = integer -> integer.compareTo(0) > 0; // 只返回布尔类型
        System.out.println(predicate.test(1));
        System.out.println(((BiPredicate<Integer, Integer>) ((Integer i1, Integer i2) -> i1 > i2))
                .test(ThreadLocalRandom.current().nextInt(3), 2)); // 两个参数

    }

    // 简单使用介绍
    private static void demo1() {
        Runnable task = new Runnable() {
            @Override
            public void run() {
                System.out.println("hello task");
            }
        };
        task.run();

        System.out.println("---");
        Runnable task2 = () -> System.out.println("hello task2");
        task2.run();

        System.out.println("-----------------------------------");
        Comparator<Integer> comparator = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        };
        System.out.println(comparator.compare(1, 2));

        System.out.println("---");
        Comparator<Integer> comparator2 = (o1, o2) -> o1.compareTo(o2);
        System.out.println(comparator2.compare(1, 2));

        System.out.println("---");
        Comparator<Integer> comparator3 = Integer::compareTo;
        System.out.println(comparator3.compare(1, 2));
    }


}
