package com.nacrt.demo.jdknew.jdk8.a_lambda;

import java.util.Arrays;
import java.util.Comparator;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.*;

/**
 * 方法引用demo(Lambda表达式另外的表示形式)
 */
public class Demo02MethodRef {

    public static void main(String[] args) {
        demo1();
    }

    private static void demo1() {
        // 1.无参有返回值
        //Supplier<Demo2> aNew = () -> new Demo2();
        Supplier<Demo02MethodRef> aNew2 = Demo02MethodRef::new; // 构造器方法引用
        System.out.println(aNew2.get());
        Supplier<Integer> sup2 = ThreadLocalRandom.current()::nextInt; // 对象方法引用
        System.out.println(sup2.get());

        System.out.println("----");
        // 2.有参无返回值
        Consumer<String> consumer = System.out::println; // 对象方法引用
        consumer.accept("hello");

        System.out.println("----");
        // 3.有参有返回值
        Function<String, Integer> function = Integer::valueOf; // (p1) 类方法引用
        System.out.println(function.apply("3333"));
        Function<Integer, String[]> conFun = String[]::new; // 数组构造器引用
        System.out.println(Arrays.toString(conFun.apply(5))); // 参数为初始化数组大小
        BiFunction<Integer, Integer, Integer> biFunction = Integer::sum; // (p1,p2) 类方法引用
        System.out.println(biFunction.apply(31, 32));
        Comparator<Integer> compare = Integer::compare; // (p1,p2) 类方法引用
        System.out.println(compare.compare(31, 32));
        // 调用参数1的方法并且使用参数2作为参数 (p1, p2) -> p1.fun(p2)
        Comparator<String> stringComparator = String::compareTo; // p1.compareTo(p2) 参数1的方法引用
        System.out.println(stringComparator.compare("31", "32"));

    }

}
