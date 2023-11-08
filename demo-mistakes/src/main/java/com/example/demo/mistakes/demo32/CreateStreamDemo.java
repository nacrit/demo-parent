package com.example.demo.mistakes.demo32;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author zhenghao
 * @description 创建流一般有五种方式：
 * @date 2020/6/19 14:14
 */
public class CreateStreamDemo {
    public static void main(String[] args) {
//        demo1();
//        demo2();
//        demo3();
//        demo4();
        demo5();
    }

    /**
     * 通过 stream 方法把 List 或数组转换为流；
     */
    public static void demo1() {
        Arrays.asList("a1", "a2", "a3").stream().forEach(System.out::println);
        Arrays.stream(new int[]{1, 2, 3}).forEach(System.out::println);
    }

    /**
     * 通过 Stream.of 方法直接传入多个元素构成一个流；
     */
    public static void demo2() {
        Stream.of(1, 2, 3, "4", true).forEach(System.out::println);
        Stream.of(new String[]{"1", "2", "3"}).forEach(System.out::println);
    }
    /**
     * 通过 Stream.iterate 方法使用迭代的方式构造一个无限流，然后使用 limit 限制流元素个数；
     */
    public static void demo3() {
        Stream.iterate(2, x -> x * 2).limit(10).forEach(System.out::println);
        Stream.iterate(BigDecimal.ZERO, x -> x.add(BigDecimal.TEN)).skip(5).limit(5).forEach(System.out::println);
    }
    /**
     * 通过 Stream.generate 方法从外部传入一个提供元素的 Supplier 来构造无限流，然后使用 limit 限制流元素个数；
     */
    public static void demo4() {
        Stream.generate(() -> "hello world").limit(5).forEach(System.out::println);
        Stream.generate(Math::random).limit(10).forEach(System.out::println);

    }
    /**
     * 通过 IntStream 或 DoubleStream 构造基本类型的流。
     */
    public static void demo5() {
        // IntStream
        IntStream.range(0, 3).forEach(System.out::println);
        IntStream.rangeClosed(0, 3).forEach(System.out::println);

        // DoubleStream
        DoubleStream.of(1.1, 2.1, 2.3).forEach(System.out::println);

        // 各种转换，后面注释代表了输出结果
        System.out.println(IntStream.of(1, 3).toArray().getClass());    // class [I
        System.out.println(Stream.of(1, 2).mapToInt(Integer::intValue).toArray().getClass()); //class [I
        System.out.println(IntStream.of(1, 2).boxed().toArray().getClass()); //class [Ljava.lang.Object;
        System.out.println(IntStream.of(1, 2).asDoubleStream().toArray().getClass()); //class [D
        System.out.println(IntStream.of(1, 2).asLongStream().toArray().getClass()); //class [J

        //注意基本类型流和装箱后的流的区别
        Stream.of("a", "b", "c")   // Stream<String>
                .mapToInt(String::length)       // IntStream
                .asLongStream()                 // LongStream
                .mapToDouble(x -> x / 10.0)     // DoubleStream
                .boxed()                        // Stream<Double>
                .mapToLong(x -> 1L)             // LongStream
                .mapToObj(x -> "x")              // Stream<String>
                .collect(Collectors.toList())   // List<String>
                .forEach(System.out::println);
    }
}
