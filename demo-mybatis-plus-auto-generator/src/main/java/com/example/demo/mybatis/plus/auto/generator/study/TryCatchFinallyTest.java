package com.example.demo.mybatis.plus.auto.generator.study;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author zhenghao
 * @description 测试
 * @date 2020/6/11 13:42
 */
public class TryCatchFinallyTest {
    /*
        finally 代码块的原理是复制 finally 代码块的内容，分别放在 try-catch 代码块所有正常执行路径以及异常执行路径的出口中。
        所以不管是是正常还是异常执行，finally都是最后执行的，所以肯定是finally语句块中为准。

        JVM采用异常表控制try-catch的跳转逻辑；
        对于finally中的代码块其实是复制到try和catch中的return和throw之前的方式来处理的。
     */
    public static void main(String[] args) {
        System.out.println("t1 return后:" + t1());
        System.out.println("---------------------------");
        System.out.println("t2 return后:" + t2());
        System.out.println("---------------------------");
        System.out.println("t3 return后:" + t3());
        System.out.println("---------------------------");
        System.out.println("t4 return后:" + t4().get());
        System.out.println("---------------------------");
        System.out.println("t5 return后:" + t5());
    }

    private static int t1 () {
        int i = 1;
        try {
            System.out.println("t1 return前:" + i);
            return i;
        } finally {
            ++i;
//            System.out.println("t1 i=" + i);
        }
    }

    private static int t2 () {
        int i = 1;
        try {
            System.out.println("t2 return前:" + i);
            return i;
        } finally {
            ++i;
//            System.out.println("t2 i=" + i);
            return i;
        }
    }

    private static Integer t3() {
        Integer i = 1;
        try {
            System.out.println("t3 return前:" + i);
            return i;
        } finally {
            ++i;
//            System.out.println("t3 i=" + i);
        }
    }

    private static AtomicInteger t4() {
        AtomicInteger ai = new AtomicInteger(1);
        try {
            System.out.println("t4 return前:" + ai.get());
            return ai;
        } finally {
            ai.incrementAndGet();
//            System.out.println("t4 ai=" + ai.get());
        }
    }

    private static Integer t5() {
        Integer i = new Integer("1");
        try {
            System.out.println("t4 return前:" + i);
            return num(i, false);
        } finally {
            i = num(i, true);
        }
    }

    private static Integer num(Integer num, boolean isIncrement) {
        return isIncrement ? num + 1 : num;
    }
}
