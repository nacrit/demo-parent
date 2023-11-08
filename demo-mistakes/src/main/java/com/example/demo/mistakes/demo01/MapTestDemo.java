package com.example.demo.mistakes.demo01;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;
import org.springframework.util.StopWatch;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.LongAdder;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author zhenghao
 * @description 01 | 使用了并发工具类库，线程安全就高枕无忧了吗？ 测试Demo
 * 使用 Map 来统计 Key 出现次数的场景吧，这个逻辑在业务代码中非常常见。
 * 使用 ConcurrentHashMap 来统计，Key 的范围是 10。使用最多 10 个并发，循环操作 1000 万次，
 * 每次操作累加随机的 Key。如果 Key 不存在的话，首次设置值为 1。
 * @date 2020/6/4 23:34
 */
@Slf4j
public class MapTestDemo {
    //循环次数
    private static int LOOP_COUNT = 10000000;
    //线程数量
    private static int THREAD_COUNT = 10;
    //元素数量
    private static int ITEM_COUNT = 10;

    public static void main(String[] args) throws InterruptedException {
//        System.out.println(normalUse());    // 改进前的代码
//        System.out.println(goodUse());  // 改进后的代码
//        testMap();
        mapTest();
    }

    public static void testMap() throws InterruptedException {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start("normalUse");
        Map<String, Long> normalUse = normalUse();      // 改进前的代码
        stopWatch.stop();
        //校验元素数量
        Assert.isTrue(normalUse.size() == ITEM_COUNT, "normalUse size error");
        //校验累计总数
        Assert.isTrue(normalUse.values().stream().reduce(0L, Long::sum) == LOOP_COUNT
                , "normalUse count error");
        stopWatch.start("goodUse");
        Map<String, Long> goodUse = goodUse();    // 改进后的代码
        stopWatch.stop();
        Assert.isTrue(goodUse.size() == ITEM_COUNT, "goodUse size error");
        Assert.isTrue(goodUse.values().stream().reduce(0L, Long::sum) == LOOP_COUNT
                , "goodUse count error");
        log.info(stopWatch.prettyPrint());
    }

    // 一般实现
    private static Map<String, Long> normalUse() throws InterruptedException {
        ConcurrentHashMap<String, Long> freqs = new ConcurrentHashMap<>(ITEM_COUNT);
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        forkJoinPool.execute(() -> IntStream.rangeClosed(1, LOOP_COUNT).parallel().forEach(i -> {
            synchronized (freqs) {
                String nextInt = "item" + ThreadLocalRandom.current().nextInt(ITEM_COUNT);
                if (freqs.containsKey(nextInt)) {
                    freqs.put(nextInt, freqs.get(nextInt) + 1);
                } else {
                    freqs.put(nextInt, 1L);
                }
            }
        }));
        forkJoinPool.shutdown();
        forkJoinPool.awaitTermination(1, TimeUnit.HOURS);
        return freqs;
    }


    // 改进后的代码
    private static Map<String, Long> goodUse() throws InterruptedException {
        /*
         * LongAdderLongAdder类与AtomicLong类的区别在于高并发时前者将对单一变量的CAS操作分散为对数组cells中多个元素的CAS操作，取值时进行求和；
         * 而在并发较低时仅对base变量进行CAS操作，与AtomicLong类原理相同。不得不说这种分布式的设计还是很巧妙的。
         */
        ConcurrentHashMap<String, LongAdder> freqs = new ConcurrentHashMap<>(ITEM_COUNT);
//        ConcurrentHashMap<String, AtomicLong> freqs = new ConcurrentHashMap<>(ITEM_COUNT);
        ForkJoinPool forkJoinPool = new ForkJoinPool(THREAD_COUNT);
        forkJoinPool.execute(() -> IntStream.rangeClosed(1, LOOP_COUNT).parallel().forEach(i -> {
                    String key = "item" + ThreadLocalRandom.current().nextInt(ITEM_COUNT);
                    //利用computeIfAbsent()方法来实例化LongAdder，然后利用LongAdder来进行线程安全计数
                    freqs.computeIfAbsent(key, k -> new LongAdder()).increment();   // 最佳实践
//                    freqs.computeIfAbsent(key, k -> (freqs.get(k) == null ? new LongAdder() : freqs.get(k))).increment();

                    // 使用 putIfAbsent
//                    LongAdder newVal = new LongAdder();
//                    newVal.increment();
//                    LongAdder oldVal = freqs.putIfAbsent(key, newVal);
//                    if (oldVal != null) oldVal.increment();

                    // 使用compute
//                    freqs.compute(key, (k, v) -> {
//                        LongAdder newVal = new LongAdder();
//                        if (v == null)
//                            v = newVal;
//                        v.increment();
//                        return v;
//                    });

                    // 使用merge
//                    freqs.merge(key, new LongAdder(), (oldVal, newVal) -> oldVal).increment();

                    // 使用AtomicLong
//                    freqs.computeIfAbsent(key, k -> new AtomicLong()).incrementAndGet();
                }
        ));
        forkJoinPool.shutdown();
        forkJoinPool.awaitTermination(1, TimeUnit.HOURS);

        //因为我们的Value是LongAdder而不是Long，所以需要做一次转换才能返回
        return freqs.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        e -> e.getValue().longValue())
                );
    }


    /*
    computeIfAbsent和putIfAbsent区别是三点：
        1.第二个参数不一样，putIfAbsent是值，computeIfAbsent是mappingFunction
        2.返回值不一样，putIfAbsent是之前的值，computeIfAbsent是现在的值
        3.putIfAbsent可以存入null（在HashMap中的），computeIfAbsent计算结果是null只会返回null，不会写入。
     共同点：在放入数据时，如果存在重复的key，不会放入值

     computeIfAbsent：当key存在时，mappingFunction不会执行，直接返回oldVal，如果key不存在，执行函数并返回newVal
     computeIfPresent：当key存在时，执行remappingFunction，返回结果作为新value，当key不存在时，不操作，返回null
     compute：不管key存在不存在都执行remappingFunction，并返回新值, 如果remappingFunction为null，key存在会remove原来的kv，key不存在不操作，都返回null
     merge：如果key不存在，不执行remappingFunction，直接设置key,value，如果key存在，执行remappingFunction，返回结果作为新的value
     replace：仅当当前映射到某个值时才替换该value
     */

    public static void mapTest() {
        Map<String, AtomicInteger> map = new HashMap<>();
        AtomicInteger world = map.computeIfAbsent("world", (key) -> {
            System.out.println("computeIfAbsent world1");
            return new AtomicInteger(1);
        });
        AtomicInteger world2 = map.computeIfAbsent("world", (key) -> {
            System.out.println("computeIfAbsent world2");
            return new AtomicInteger(2);
        });
        System.out.println("world:" + world);   // 不存在 执行函数得到新值put并返回新值
        System.out.println("world2:" + world2); // 存在 不执行函数 返回旧值
        map.put("hello", new AtomicInteger());
        AtomicInteger hello = map.computeIfPresent("hello", (key, old) -> new AtomicInteger(3));
        AtomicInteger java = map.computeIfPresent("java", (key, old) -> new AtomicInteger(4));
        System.out.println("hello:" + hello);   // 存在 执行函数得到新值 设置并返回新值
        System.out.println("java:" + java);     // 不存在，不操作，返回null

        AtomicInteger test = map.putIfAbsent("test", new AtomicInteger(5));
        AtomicInteger test2 = map.putIfAbsent("test", new AtomicInteger(6));
        System.out.println("test:" + test);       // 不存在 执行函数得到新值put并返回旧值（null）
        System.out.println("test2:" + test2);     // 存在 不执行函数 返回旧值

        AtomicInteger compute = map.compute("compute", (key, oldVal) -> new AtomicInteger(7));
        AtomicInteger compute2 = map.compute("compute", (key, oldVal) -> new AtomicInteger(8));
        System.out.println("compute:" + compute);   // 不存在 执行函数 设置并返回新值
        System.out.println("compute2:" + compute2); // 存在 执行函数 设置并返回新值

        map.put("merge", new AtomicInteger());
        // merge(K key, V newVal, BiFunction<V,V,V> fun)
        AtomicInteger merge = map.merge("merge", new AtomicInteger(9), (oldVal, newVal) -> {
            System.out.println("--------merge," + oldVal + "," + newVal);
            return newVal;
        });
        AtomicInteger merge2 = map.merge("merge2", new AtomicInteger(10), (oldVal, newVal) -> {
            System.out.println("========merge2," + oldVal + "," + newVal);
            return newVal;
        });
        System.out.println("merge:" + merge);   // 存在 执行函数 设置并返回新值
        System.out.println("merge2:" + merge2); // 不存在 不执行函数 设置新值并返回

        System.out.println("------------------------------------------------");
        map.put("replace", new AtomicInteger(11));
        AtomicInteger newVal = new AtomicInteger(12);
        AtomicInteger replace = map.replace("replace", newVal);
        System.out.println("replace:" + replace);   // 存在 新替换旧， 返回旧
        System.out.println("getReplace:" + map.get("replace"));
        boolean isReplace = map.replace("replace", new AtomicInteger(11), new AtomicInteger(13));
        boolean isReplace2 = map.replace("replace", new AtomicInteger(12), new AtomicInteger(14));
        boolean isReplace3 = map.replace("replace", newVal, new AtomicInteger(15));
        System.out.println("isReplace:" + isReplace);   // key和oldVal不匹配 不替换 返回false
        System.out.println("isReplace2:" + isReplace2); // 注意AtomicInteger的equals方法未重写，oldVal依然不匹配
        System.out.println("isReplace3:" + isReplace3); // key和oldVal匹配 替换 返回true
        System.out.println("getReplace:" + map.get("replace"));
        System.out.println("----------------------------");
        map.replaceAll((key, oldVal) -> new AtomicInteger(oldVal.get() + 100));
        map.forEach((k, v) -> System.out.println("key:" + k + ",val:" + v));    // 函数返回结果替换所有val

        System.out.println("-------------------------------------------");
        AtomicInteger xxx = map.compute("comput-xxx", (k, v) -> v == null ? new AtomicInteger(1) : new AtomicInteger(v.incrementAndGet()));
        map.put("comput-yyyy", new AtomicInteger(1));
        AtomicInteger yyy = map.compute("comput-yyyy", (k, v) -> null);
        System.out.println(map + "\nxxx=" + xxx + ", yyy=" + yyy);
    }
}

