package com.acrt.example.demo_juc;



import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.LockSupport;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j(topic = "c.myAQSTest")
public class MyAQSTest {

    @Test
    void test2() throws InterruptedException {
        List<Thread> list = IntStream.range(0, 10).mapToObj(i -> new Thread(() -> {
            try {
                test();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        })).collect(Collectors.toList());
        list.forEach(Thread::start);
        list.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
    }


    @Test
    void test() throws InterruptedException {
        int[] count = new int[]{20};
        List<Thread> threads = new ArrayList<>();
        MyLock lock = new MyLock();
        for (int i = 0; i < 2; i++) {
            threads.add(new Thread(() -> {
                lock.lock();
                for (int j = 0; j < 10; j++) {
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    count[0]--;
                }
                lock.unlock();
            }, "thread-" + i));
        }

        for (Thread thread : threads) {
            thread.start();
        }
        for (Thread thread : threads) {
            thread.join();
        }
        log.info("count:{}", count[0]);
        if (count[0] != 0) {
            throw new RuntimeException("error...... count:" + count[0]);
        }
    }
}
class MyLock {
    AtomicBoolean locked = new AtomicBoolean(false);
    Thread owner = null;
    AtomicReference<Node> head = new AtomicReference<>(new Node());
    AtomicReference<Node> tail = new AtomicReference<>(head.get());


    public void lock() {
//        if (locked.compareAndSet(false, true)) {
//            owner = Thread.currentThread();
//            System.out.println(owner.getName() + " 直接获取到了锁✅✅✅✅✅");
//            return;
//        }
        Node currentNode = new Node();
        currentNode.currentThread = Thread.currentThread();
        while (true) {
            Node tailNode = tail.get();
            // 加入到尾节点
            if (tail.compareAndSet(tailNode, currentNode)) {
                System.out.println(currentNode.currentThread.getName() + " 加入到尾队列⌛️⌛️⌛️⌛️⌛️");
                currentNode.prev = tailNode;
                tailNode.next = currentNode;
                break;
            }
        }
        while (true) {
            if (head.get() == currentNode.prev && locked.compareAndSet(false, true)) {
                System.out.println(currentNode.currentThread.getName() + " 获取到了锁✅✅✅");
                owner = Thread.currentThread();
                head.set(currentNode);
                // 删除原来的头节点
                currentNode.prev.next = null;
                currentNode.prev = null;
                return;
            }
            if (Thread.currentThread().getName().equals("thread-1")) {
                System.out.println(currentNode.currentThread.getName() + " 准备park");
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            System.out.println(currentNode.currentThread.getName() + " park");
            LockSupport.park();
        }
    }
    public void unlock() {
        if (owner != Thread.currentThread()) {
            System.out.println("owner.getName() = " + owner.getName() + ", currentName=" + Thread.currentThread().getName());
            throw new RuntimeException("unlock failed");
        }
        System.out.println(owner.getName() + " 释放锁 ... " + head);
        Node next = head.get().next;
        locked.set(false);
        if (next != null) {
            System.out.println(owner.getName() + " 准备释放锁，唤醒下一个线程 " + next.currentThread.getName());
            try {
                Thread.sleep(500L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            LockSupport.unpark(next.currentThread);
            System.out.println(owner.getName() + " 释放了锁 --> 唤醒下一个线程 " + next.currentThread.getName());
        }
    }

    static class Node {
        Node next;
        Node prev;
        Thread currentThread;

        @Override
        public String toString() {
            return (currentThread != null ? currentThread.getName() : "null") + (next != null ? " --> " + next : "");
        }
    }
}
