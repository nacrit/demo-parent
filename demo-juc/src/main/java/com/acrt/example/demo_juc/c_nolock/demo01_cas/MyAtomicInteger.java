package com.acrt.example.demo_juc.c_nolock.demo01_cas;

import com.acrt.example.demo_juc.util.SleepUtils;
import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * 自己实现AtomicInteger功能
 *
 * @author zhenghao
 * @date 2022/4/6 14:58
 */
public class MyAtomicInteger {
    // 值
    private volatile int value;
    // 偏移量
    private static final long valueOffset;
    private static final Unsafe unsafe = UnsafeUtil.getUnsafe();

    static {
        try {
            valueOffset = unsafe.objectFieldOffset( MyAtomicInteger.class.getDeclaredField("value"));
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public MyAtomicInteger() {
        this(0);
    }

    public void setValue(int value) {
        this.value = value;
    }

    public MyAtomicInteger(int value) {
        this.value = value;
    }

    public int get() {
        return value;
    }

    public int incrementAndGet() {
        while (true) {
            int oldVal = get();
            int newVal = oldVal + 1;
            if (compareAndSet(oldVal, newVal)) {
                return newVal;
            }
        }
//        return ++this.value;
    }

    public int getAndIncrement() {
        while (true) {
            int oldVal = get();
            int newVal = oldVal + 1;
            if (compareAndSet(oldVal, newVal)) {
                return oldVal;
            }
        }
    }

    public boolean compareAndSet(int oldVal, int newVal) {
        return unsafe.compareAndSwapInt(this, valueOffset, oldVal, newVal);
    }


    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        demo1();
        long end = System.currentTimeMillis();
        demo2();
        long end2 = System.currentTimeMillis();
        System.out.println("useTime = " + (end - start) + "ms");
        System.out.println("useTime2 = " + (end2 - end) + "ms");
    }

    private static void demo2() {

        List<Thread> threadList = new ArrayList<>();
        MyAtomicInteger myAtomicInteger = new MyAtomicInteger();
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                threadList.add(Thread.currentThread());
                for (int j = 0; j < 1_000_000; j++) {
                    myAtomicInteger.incrementAndGet();
                }
            }, "t" + i).start();
        }
        SleepUtils.sleep(2);
        for (Thread t : threadList) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("result2=" + myAtomicInteger.get());
    }
    private static void demo1() {
        List<Thread> threadList = new ArrayList<>();
        MyAtomicInteger myAtomicInteger = new MyAtomicInteger();
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                threadList.add(Thread.currentThread());
                for (int j = 0; j < 1_000_000; j++) {
                    myAtomicInteger.incrementAndGet();
                }
            }, "t" + i).start();
        }
        SleepUtils.sleep(2);
        for (Thread t : threadList) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("result=" + myAtomicInteger.get());
    }
}
