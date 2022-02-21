package com.nacrt.demo.jdknew.jdk8.b_stream;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.*;

/**
 * stream 特性
 *  stream 是计算使用的（与cpu打交道）不存储数据、执行终止操作才会开始运算
 */
public class Demo01Stream {

    public static void main(String[] args) {
        // 1.创建方式
        demo1Create();

        // 2.中间操作
        // 3.终止操作
        demo2Operation();

    }

    /**
     * 流的操作：把一个数据结构包装成 Stream 后，就要开始对里面的元素进行各类操作了。常见的操作可以归类如下。
     *      Intermediate：中间处理
     *          map (mapToInt, flatMap 等)、 filter、 distinct、 sorted、
     *          peek、 limit、 skip、 parallel、 sequential、 unordered
     *      Terminal：最终操作
     *          forEach、 forEachOrdered、 toArray、 reduce、 collect、 min、 max、
     *          count、 anyMatch、 allMatch、 noneMatch、 findFirst、 findAny、 iterator
     *      Short-circuiting：短路循环
     *          anyMatch、 allMatch、 noneMatch、 findFirst、 findAny、 limit
     */
    private static void demo2Operation() {
        List<Integer> list = IntStream.range(0, 10)
                .mapToObj(i -> ThreadLocalRandom.current().nextInt(20) + 5)
                .collect(Collectors.toList());
        System.out.println(list);
        Collections.sort(list);
        System.out.println(list);
        List<Integer> sortedRes = list.stream()
                .distinct() // 去重
                .sorted(Comparator.reverseOrder()) // 倒叙
                .skip(1) // skip
                .limit(3) // limit
                .collect(Collectors.toList());
        System.out.println(sortedRes);
        System.out.println("---");
        // flatMap
        Stream<String[]> arrStream = Stream.of(new String[]{"hello", "world"}, new String[]{"test", "java"});
        System.out.println(arrStream.flatMap(Arrays::stream).collect(Collectors.toList()));

        System.out.println(list.stream().allMatch(i -> i > 5)); // 所有都匹配
        System.out.println(list.stream().anyMatch(i -> i < 7)); // 至少一个匹配
        System.out.println(list.stream().noneMatch(i -> i < 5)); // 都不匹配
        System.out.println(list.stream().findFirst().orElse(-1)); // 取第一个元素
        System.out.println(list.parallelStream().findAny().orElse(-1)); // 任取一个元素

    }

    // 创建流的方式
    private static void demo1Create() {
        // 1.1 通过集合创建
        Stream<String> stream = Collections.singleton("hello").stream();
        Stream<String> parallelStream = Collections.singleton("hello").parallelStream();
        // 1.2 Stream创建
        Stream<Integer> intStream = Stream.of(11, 12);
        Stream<Object> objStream = Stream.builder().add("hello").add(222).build();
        // 1.3 通过数组创建
        Stream<String> strStream = Arrays.stream(new String[]{"hello", "world"});
        // 1.4 创建无限流
        Stream<Integer> iterate = Stream.iterate(0, a -> a + 1);
        //iterate.limit(10).forEach(System.out::println);

        // 1.5 其他扩展
        IntStream range = IntStream.range(1, 10);
        LongStream longStream = LongStream.range(0, 1000L);
        DoubleStream doubleStream = DoubleStream.of(1.1, 2.2, 3.3);
        // ...
    }


}
