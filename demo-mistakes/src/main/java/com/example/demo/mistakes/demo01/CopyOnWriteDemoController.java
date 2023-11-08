package com.example.demo.mistakes.demo01;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author zhenghao
 * @description 在 Java 中，CopyOnWriteArrayList 虽然是一个线程安全的 ArrayList，但因为其实现方式是，每次修改数据时都会复制一份数据出来，所以有明显的适用场景，即读多写少或者说希望无锁读的场景。
 * 它是个线程安全且读操作无锁的ArrayList，写操作则通过创建底层数组的新副本来实现，是一种读写分离的并发策略，我们也可以称这种容器为"写时复制器"
 *    优点：
 *      Java的list在遍历时，若中途有别的线程对list容器进行修改，则会抛出ConcurrentModificationException异常。
 *      而CopyOnWriteArrayList由于其"读写分离"的思想，遍历和修改操作分别作用在不同的list容器，所以在使用迭代器进行遍历时候，也就不会抛出ConcurrentModificationException异常了
 *    缺点：占内存，无法保证实时性，写的性能极差
 *   为什么CopyOnWriteArrayList添加慢：以 add 方法为例，每次 add 时，都会用 Arrays.copyOf 创建一个新数组，频繁 add 时内存的申请释放消耗会很大
 *
 *  SynchronizedList和Vector最主要的区别：
 *      Vector扩容为原来的2倍长度，ArrayList扩容为原来1.5倍(jdk1.8中)
 *      SynchronizedList有很好的扩展和兼容功能。他可以将所有的List的子类转成线程安全的类。
 *      使用SynchronizedList的时候，进行遍历时要手动进行同步处理 。
 *  注：SynchronizedList和Vector使用迭代器遍历都是线程不安全的
 *
 *  ArrayList序列化使用readObject和writeObject是为了 保证只序列化实际存储的那些元素，而不是整个数组
 *
 * @date 2020/6/15 13:14
 */
@RestController
@RequestMapping("/cow")
@Slf4j
public class CopyOnWriteDemoController {
    //测试并发写的性能
    @GetMapping("/write")
    public Map<String, Integer> testWrite() {
        List<Integer> copyOnWriteArrayList = new CopyOnWriteArrayList<>();
        List<Integer> synchronizedList = Collections.synchronizedList(new ArrayList<>());
        Vector<Integer> vector = new Vector<>();
        StopWatch stopWatch = new StopWatch();
        int loopCount = 100000; // 十万次
        stopWatch.start("Write:copyOnWriteArrayList");
        //循环100000次并发往CopyOnWriteArrayList写入随机元素
        IntStream.rangeClosed(1, loopCount).parallel().forEach(__ -> copyOnWriteArrayList.add(ThreadLocalRandom.current().nextInt(loopCount)));
        stopWatch.stop();
        stopWatch.start("Write:synchronizedList");
        //循环100000次并发往加锁的ArrayList写入随机元素
        IntStream.rangeClosed(1, loopCount).parallel().forEach(__ -> synchronizedList.add(ThreadLocalRandom.current().nextInt(loopCount)));
        stopWatch.stop();
        stopWatch.start("Write:vector");
        //循环100000次并发往加锁的ArrayList写入随机元素
        IntStream.rangeClosed(1, loopCount).parallel().forEach(__ -> vector.add(ThreadLocalRandom.current().nextInt(loopCount)));
        stopWatch.stop();
        log.info(stopWatch.prettyPrint());
        Map<String, Integer> result = new HashMap<>();
        result.put("copyOnWriteArrayList", copyOnWriteArrayList.size());
        result.put("synchronizedList", synchronizedList.size());
        result.put("vector", vector.size());
        return result;
    }

    //帮助方法用来填充List
    private void addAll(List<Integer> list) {
        list.addAll(IntStream.rangeClosed(1, 1000000).boxed().collect(Collectors.toList()));    // 100万次
    }

    //测试并发读的性能
    @GetMapping("/read")
    public Map<String, Integer> testRead() {
        //创建两个测试对象
        List<Integer> copyOnWriteArrayList = new CopyOnWriteArrayList<>();
        List<Integer> synchronizedList = Collections.synchronizedList(new ArrayList<>());
        Vector<Integer> vector = new Vector<>();
        List<Integer> arrayList =new ArrayList<>();
        //填充数据
        addAll(copyOnWriteArrayList);
        addAll(synchronizedList);
        addAll(vector);
        addAll(arrayList);
        StopWatch stopWatch = new StopWatch();
        int loopCount = 1000000;    // 100万次
        int count = copyOnWriteArrayList.size();
        stopWatch.start("Read:copyOnWriteArrayList");
        //循环1000000次并发从CopyOnWriteArrayList随机查询元素
        IntStream.rangeClosed(1, loopCount).parallel().forEach(__ -> copyOnWriteArrayList.get(ThreadLocalRandom.current().nextInt(count)));
        stopWatch.stop();
        stopWatch.start("Read:synchronizedList");
        //循环1000000次并发从加锁的ArrayList随机查询元素
        IntStream.range(0, loopCount).parallel().forEach(__ -> synchronizedList.get(ThreadLocalRandom.current().nextInt(count)));
        stopWatch.stop();
        stopWatch.start("Read:vector");
        //循环1000000次并发从加锁的ArrayList随机查询元素
        IntStream.range(0, loopCount).parallel().forEach(__ -> vector.get(ThreadLocalRandom.current().nextInt(count)));
        stopWatch.stop();
        stopWatch.start("Read:arrayList");
        //循环1000000次并发从加锁的ArrayList随机查询元素
        IntStream.range(0, loopCount).parallel().forEach(__ -> arrayList.get(ThreadLocalRandom.current().nextInt(count)));
        stopWatch.stop();
        log.info(stopWatch.prettyPrint());
        Map<String, Integer> result = new HashMap<>();
        result.put("copyOnWriteArrayList", copyOnWriteArrayList.size());
        result.put("synchronizedList", synchronizedList.size());
        result.put("vector", vector.size());
        result.put("arrayList", arrayList.size());
        return result;
    }

    /*
    结论：
        2020-06-15 13:39:46.911  INFO 10800 --- [nio-8081-exec-1] c.j.e.e.d.error.demo01.CopyOnWriteDemo   : StopWatch '': running time = 6065900200 ns
        ---------------------------------------------
        ns         %     Task name
        ---------------------------------------------
        6042045200  100%  Write:copyOnWriteArrayList
        023855000  000%  Write:synchronizedList

        2020-06-15 13:42:14.711  INFO 10800 --- [nio-8081-exec-1] c.j.e.e.d.error.demo01.CopyOnWriteDemo   : StopWatch '': running time = 343420000 ns
        ---------------------------------------------
        ns         %     Task name
        ---------------------------------------------
        016823100  005%  Read:copyOnWriteArrayList
        326596900  095%  Read:synchronizedList

        大量写的场景（10 万次 add 操作），CopyOnWriteArray 几乎比同步的 ArrayList 慢一百倍,
        而在大量读的场景下（100 万次 get 操作），CopyOnWriteArray 又比同步的 ArrayList 快五倍以上;
        copyOnWriteArrayList：写慢读快
        为何在大量写的场景下，CopyOnWriteArrayList 会这么慢呢？
            答案就在源码中，以 add 方法为例，每次 add 时，都会用 Arrays.copyOf 创建一个新数组，频繁 add 时内存的申请释放消耗会很大




     */
}
