package com.nacrt.demo.jdknew.jdk5;

import java.util.ArrayList;
import java.util.Arrays;

import static java.lang.Integer.sum;

/**
 * Demo
 *
 * @author zhenghao
 * @date 2022/3/30 21:46
 */
public class Demo {
    public static void main(String[] args) {
        // 泛型
        System.out.println("demo1(1) = " + demo1(1));
        System.out.println("demo1(\"1\") = " + demo1("1"));
        // 枚举
        demo2();
        // 可变参数
        demo3(3, 3);
        // 自动拆装箱
        demo4(4);
        // 静态导入
        System.out.println("demo5(5, 5) = " + demo5(5, 5));
        // 增强for
        demo6(new int[]{6});
    }

    private static void demo6(int[] arr) {
        for (int i : arr) {
            System.out.println("i = " + i);
        }
    }

    private static int demo5(int a, int b) {
        return sum(a, b);
    }

    private static void demo4(Integer i) {
        int j = 2;
        System.out.println("i * j = " + i * j);
    }

    private static void demo3(int... param) {
        System.out.println("param = " + Arrays.toString(param));
    }

    private static void demo2() {
        System.out.println("MyEnum.A = " + MyEnum.A);
        System.out.println("MyEnum.B = " + MyEnum.B);
    }

    // 泛型
    private static <T> T demo1(T t) {
        ArrayList<String> list = new ArrayList<>();
        return t;
    }

    enum MyEnum {
        A, B, C
    }
}
