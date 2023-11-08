package com.example.demo.mistakes.demo32;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

/**
 * @author zhenghao
 * @description 测试
 * @date 2020/6/23 11:23
 */
public class MyCollectors {
    private static final Set<Collector.Characteristics> CH_CONCURRENT = Collections.unmodifiableSet(EnumSet.of(Collector.Characteristics.CONCURRENT));
    private MyCollectors() {
    }
    static class CollectorImpl<T, A, R> implements Collector<T, A, R> {
        private final Supplier<A> supplier;
        private final BiConsumer<A, T> accumulator;
        private final BinaryOperator<A> combiner;
        private final Function<A, R> finisher;
        private final Set<Characteristics> characteristics;

        CollectorImpl(Supplier<A> supplier,
                      BiConsumer<A, T> accumulator,
                      BinaryOperator<A> combiner,
                      Function<A, R> finisher,
                      Set<Characteristics> characteristics) {
            this.supplier = supplier;
            this.accumulator = accumulator;
            this.combiner = combiner;
            this.finisher = finisher;
            this.characteristics = characteristics;
        }
        @Override
        public BiConsumer<A, T> accumulator() {
            return accumulator;
        }

        @Override
        public Supplier<A> supplier() {
            return supplier;
        }

        @Override
        public BinaryOperator<A> combiner() {
            return combiner;
        }

        @Override
        public Function<A, R> finisher() {
            return finisher;
        }

        @Override
        public Set<Characteristics> characteristics() {
            return characteristics;
        }
    }
    public static <T> Collector<T, Map<T, Integer>, Optional<T>> getCountMax() {
        return new CollectorImpl<>(
                HashMap::new,
                (acc, t) ->
                        acc.compute(t, (key, val) -> val == null ? 1 : val + 1),
                (m1, m2) -> {
                    m1.forEach((key, count) -> m2.put(key, m2.get(key) == null ? count : m2.get(key) + count));
                    return m2;
                },
                acc -> Optional.ofNullable(acc.entrySet()
                        .stream()
                        .max(Map.Entry.comparingByValue())
                        .get().getKey()),
                CH_CONCURRENT
        );
    }
}
