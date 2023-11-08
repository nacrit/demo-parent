package com.example.demo.mistakes.demo02.study;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zhenghao
 * @description ReentrantLock学习
 * @date 2020/6/16 9:30
 */
public class ReentrantLockDemo {
    private static final ReentrantLock REENTRANT_LOCK = new ReentrantLock();
    private static final Object LOCK = new Object();
    // 公平锁
    private static final ReentrantLock FAIR_LOCK = new ReentrantLock(true);
    private static final ReentrantLock REENTRANT_LOCK_A = new ReentrantLock();
    private static final ReentrantLock REENTRANT_LOCK_B = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {
        // 1.锁的基本使用
//        new Thread(ReentrantLockDemo::test, "线程1").start();
//        new Thread(ReentrantLockDemo::test, "线程2").start();
//        new Thread(ReentrantLockDemo::test2, "线程3").start();
//        new Thread(ReentrantLockDemo::test2, "线程4").start();

        // 2.公平锁的使用
//        new Thread(ReentrantLockDemo::test3, "线程A").start();
//        new Thread(ReentrantLockDemo::test3, "线程B").start();
//        new Thread(ReentrantLockDemo::test3, "线程C").start();
//        new Thread(ReentrantLockDemo::test3, "线程D").start();
//        new Thread(ReentrantLockDemo::test3, "线程E").start();

        // 3.非公平锁
//        new Thread(ReentrantLockDemo::test4, "线程A").start();
//        new Thread(ReentrantLockDemo::test4, "线程B").start();
//        new Thread(ReentrantLockDemo::test4, "线程C").start();
//        new Thread(ReentrantLockDemo::test4, "线程D").start();
//        new Thread(ReentrantLockDemo::test4, "线程E").start();

        // 4、响应中断
//        Thread threadX = new Thread(() -> test5(REENTRANT_LOCK_A, REENTRANT_LOCK_B), "线程X");
//        Thread threadY = new Thread(() -> test5(REENTRANT_LOCK_B, REENTRANT_LOCK_A), "线程Y");
//        threadX.start();
//        threadY.start();
//        Thread.sleep(2000);
//        threadY.interrupt(); // 死锁后中断一个线程

        // 5.限时等待
        new Thread(() -> test6(REENTRANT_LOCK_A, REENTRANT_LOCK_B), "线程1").start();
        Thread.sleep(3000);
        new Thread(() -> test6(REENTRANT_LOCK_B, REENTRANT_LOCK_A), "线程2").start();
        new Thread(() -> test6(REENTRANT_LOCK_A, REENTRANT_LOCK_B), "线程3").start();
        new Thread(() -> test6(REENTRANT_LOCK_B, REENTRANT_LOCK_A), "线程4").start();
    }

    private static void test() {
        try {
            if (Thread.currentThread().getName().equals("线程1")) {
                Thread.sleep(10);
            }
            REENTRANT_LOCK.lock();
            System.out.println(Thread.currentThread().getName() + "获得了锁");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println(Thread.currentThread().getName() + "释放了锁");
            REENTRANT_LOCK.unlock();
        }
    }

    private static void test2() {
        if (Thread.currentThread().getName().equals("线程3")) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        synchronized (LOCK) {
            System.out.println(Thread.currentThread().getName() + "获得了锁");
            System.out.println(Thread.currentThread().getName() + "释放了锁");
        }
    }

    private static void test3() {
        for (int i = 0; i < 2; i++) {
            try {
                FAIR_LOCK.lock();
                Thread.sleep(10);
                System.out.println(Thread.currentThread().getName() + "获得了锁");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                System.out.println(Thread.currentThread().getName() + "释放了锁");
                FAIR_LOCK.unlock();
            }
        }
    }

    private static void test4() {
        for (int i = 0; i < 2; i++) {
            String currentThreadName = Thread.currentThread().getName();
            try {
//                if (currentThreadName.contains("A") ||
//                        currentThreadName.contains("C") ||
//                        currentThreadName.contains("E")) {
//                    Thread.sleep(10);
//                }
                REENTRANT_LOCK.lock();
                System.out.println(currentThreadName + "获得了锁");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                System.out.println(currentThreadName + "释放了锁");
                REENTRANT_LOCK.unlock();
            }
        }
    }

    private static void test5(ReentrantLock fistLock, ReentrantLock secondLock) {
        try {
            fistLock.lockInterruptibly();
            Thread.sleep(10);
            secondLock.lockInterruptibly();
            System.out.println(Thread.currentThread().getName() + "获得了锁");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println(Thread.currentThread().getName() + "释放了锁");
            fistLock.unlock();
            secondLock.unlock();
        }
    }

    private static void test6(ReentrantLock firstLock, ReentrantLock secondLock) {
        try {
            if (firstLock.tryLock()) Thread.sleep(100);
            if (secondLock.tryLock()) Thread.sleep(100);
            System.out.println(Thread.currentThread().getName() + "获得了锁");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println(Thread.currentThread().getName() + "释放了锁");
            firstLock.unlock();
            secondLock.unlock();
        }
    }
}

// 官方使用示例：
class ClazzX {
    private final ReentrantLock lock = new ReentrantLock();
    // ...
    public void m() {
        lock.lock(); // block until condition holds
        try {
            // ... method body
        } finally {
            lock.unlock();
        }
    }
}
