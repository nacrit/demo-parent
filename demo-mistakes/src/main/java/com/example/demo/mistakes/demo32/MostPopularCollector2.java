package com.example.demo.mistakes.demo32;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.LongAdder;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

/**
 * @author zhenghao
 * @description 自定义转换器
 * @date 2020/6/22 16:42
 */
// Collector<T, A, R>：T 中间操作类型，A 结果类型，R 最终返回类型，一般情况下，A=R
public class MostPopularCollector2<T>
        implements Collector<T, // 收集String流
        Map<T, LongAdder>, // 累加器是一个Map，key为字符，value为出现的次数
        Optional<T> > // 返回的是出现次数最多的字符
{

    /**
     * 返回一个在调用时创建的累加器 // 源数据对象类型（中间操作对象类型）
     */
    public Supplier<Map<T, LongAdder>> supplier() {
        return ConcurrentHashMap::new;
    }

    /**
     * 定义收集流中数据逻辑 // T 中间操作类型
     */
    public BiConsumer<Map<T, LongAdder>, T> accumulator() {
        return (Map<T, LongAdder> acc, T t) ->
                acc.computeIfAbsent(t, k -> new LongAdder()).increment();
    }

    /**
     * 处理并行操作，其实就是将两个map合成一个，把value加起来 //合并部分结果
     */
    public BinaryOperator<Map<T, LongAdder>> combiner() {
        return (m1, m2) -> {
            m1.forEach((key, val) -> m2.computeIfAbsent(key, k -> new LongAdder()).add(val.longValue()));
            return m2;
        };
    }

    /**
     * 可选，对结果集的转换
     */
    public Function<Map<T, LongAdder>, Optional<T>> finisher() {
        return acc -> Optional.ofNullable(acc.entrySet()
                .stream()
                .max(Comparator.comparingLong(val -> val.getValue().longValue()))
                .get().getKey());
    }

    /**
     *  当前收集器的特性
     *   CONCURRENT,// 并发
     *   UNORDERED,// 收集操作不能保证保留顺序
     *   IDENTITY_FINISH // 标识 finisher 就是 identity（同一性） 函数，类型转换要求必须成功。 A=R时设置，此时finisher不执行
     */
    public Set<Characteristics> characteristics() {
        return Collections.unmodifiableSet(EnumSet.of(Characteristics.CONCURRENT));
    }
}
