package com.example.demo.mistakes.demo32;

import java.util.*;
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
// Collector<T, A, R>：A 结果类型，T 中间操作类型，R 最终返回类型，一般情况下，A=R
public class MostPopularCollector<T>
        implements Collector<T, // 收集String流
        Map<T, Integer>, // 累加器是一个Map，key为字符，value为出现的次数
        Optional<T>> // 返回的是出现次数最多的字符
{

    /**
     * 返回一个在调用时创建的累加器 // 源数据对象类型（中间操作对象类型）
     */
    public Supplier<Map<T, Integer>> supplier() {
        return HashMap::new;
    }

    /**
     * 定义收集流中数据逻辑 // T 中间操作类型
     */
    public BiConsumer<Map<T, Integer>, T> accumulator() {
//        return (Map<T, Integer> acc, T chart) ->
//                acc.compute(chart, (key, val) -> val == null ? 1 : val + 1); // 如果当前字符未统计则统计为1，否则+1
        return (Map<T, Integer> acc, T t) ->
                acc.compute(t, (key, val) -> val == null ? 1 : val + 1); // 如果当前字符未统计则统计为1，否则+1
    }

    /**
     * 处理并行操作，其实就是将两个map合成一个，把value加起来 //并发 合并部分结果
     */
    public BinaryOperator<Map<T, Integer>> combiner() {
        return (Map<T, Integer> m1,
                Map<T, Integer> m2) -> {
            Map<T, Integer> all = new HashMap<>(m1);
            m2.forEach((chart, count) -> all.merge(chart, count, Integer::sum));
            return all;
        };
    }

    /**
     * 可选，对结果集的转换
     */
    public Function<Map<T, Integer>, Optional<T>> finisher() {
        return (Map<T, Integer> acc) -> Optional.ofNullable(acc.entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .get().getKey());
    }

    /**
     * 当前收集器的特性
     * CONCURRENT,// 标识收集器是一个并发的
     * UNORDERED,// 收集操作不能保证保留顺序
     * IDENTITY_FINISH // 标识 finisher 就是 identity（同一性） 函数，类型转换要求必须成功。
     */
    public Set<Characteristics> characteristics() {
        return Collections.unmodifiableSet(EnumSet.of(Characteristics.CONCURRENT));
    }
}
