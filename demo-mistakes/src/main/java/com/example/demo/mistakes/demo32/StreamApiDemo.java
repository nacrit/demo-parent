package com.example.demo.mistakes.demo32;

import com.example.demo.mistakes.demo31.Product;
import com.example.demo.mistakes.demo32.model.Customer;
import com.example.demo.mistakes.demo32.model.Order;
import com.example.demo.mistakes.demo32.model.OrderItem;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.atomic.LongAdder;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

/**
 * @author zhenghao
 * @description stream方法测试
 * @date 2020/6/19 14:53
 */
public class StreamApiDemo {
    private static List<Order> orders;

    static {
        orders = Order.getData();
        orders.forEach(System.out::println);
        System.out.println("------------------------");
    }


    public static void main(String[] args) {
//        demo1();
//        demo2();
//        demo3();
//        demo4();
//        demo5();
//        demo6();
//        demo7();
//        demo8();
//        demo9();
        demo10();
    }

    /**
     * filter 方法可以实现过滤操作，类似 SQL 中的 where。
     * 我们可以使用一行代码，通过 filter方法实现查询所有订单中最近半年金额大于 40 的订单，
     * 通过连续叠加 filter 方法进行多次条件过滤：
     */
    public static void demo1() {
        //最近半年的金额大于40的订单
        orders.stream()
                .filter(Objects::nonNull) //过滤null值
                .filter(order -> order.getPlacedAt().isAfter(LocalDateTime.now().minusMonths(6))) //最近半年的订单
                .filter(order -> order.getTotalPrice() > 40) //金额大于40的订单
                .forEach(System.out::println);
        System.out.println("--------------------------------");
        // 使用循环
        for (Order order : orders) {
            if (order != null
                    && order.getPlacedAt().isAfter(LocalDateTime.now().minusMonths(6))
                    && order.getTotalPrice() > 40) {
                System.out.println(order);
            }
        }
    }

    /**
     * map 和 mapToObj
     * map 操作可以做转换（或者说投影），类似 SQL 中的 select。
     * 为了对比，我用两种方式统计订单中所有商品的数量，
     * 前一种是通过两次遍历实现，后一种是通过两次 mapToLong+sum 方法实现：
     */
    public static void demo2() {
        //计算所有订单商品数量
        //通过两次遍历实现
        LongAdder longAdder = new LongAdder();
        orders.stream().forEach(order ->
                order.getOrderItemList().forEach(orderItem -> longAdder.add(orderItem.getProductQuantity())));

        //使用两次mapToLong+sum方法实现
        System.out.println(longAdder.longValue() ==
                orders.stream().mapToLong(
                        order -> order.getOrderItemList()
                                .stream()
                                .mapToLong(OrderItem::getProductQuantity)
                                .sum()  // 统计每个订单商品数量
                ).sum());

        // mapToObj 生成 10 个 Product 元素构成 List：
        List<Product> productList = IntStream.rangeClosed(1, 10).mapToObj(i -> new Product((long) i, "product" + i, i * 100.0))
                .collect(Collectors.toList());
    }

    /**
     * flatMap
     * flatMap 展开或者叫扁平化操作，相当于 map+flat，通过 map 把每一个元素替换为一个流，然后展开这个流。
     * 我们要统计所有订单的总价格,可以有两种方式：
     * 直接通过原始商品列表的商品个数 * 商品单价统计的话，可以先把订单通过 flatMap 展开成商品清单，
     * 也就是把 Order 替换为 Stream，然后对每一个 OrderItem 用 mapToDouble 转换获得商品总价，
     * 最后进行一次 sum 求和；
     * 利用 flatMapToDouble 方法把列表中每一项展开替换为一个 DoubleStream，也就是直接把每一个订单转换为每一个商品的总价，然后求和。
     */
    public static void demo3() {
        //直接展开订单商品进行价格统计
        System.out.println(orders.stream()
                .flatMap(order -> order.getOrderItemList().stream())
                .mapToDouble(item -> item.getProductQuantity() * item.getProductPrice()).sum());

        //另一种方式flatMap+mapToDouble=flatMapToDouble
        System.out.println(orders.stream()
                .flatMapToDouble(
                        order -> order.getOrderItemList()
                                .stream().mapToDouble(item -> item.getProductQuantity() * item.getProductPrice()))
                .sum());
    }

    /**
     * sorted
     * sorted 操作可以用于行内排序的场景，类似 SQL 中的 order by。
     * 比如，要实现大于 50 元订单的按价格倒序取前 5，
     * 可以通过 Order::getTotalPrice 方法引用直接指定需要排序的依据字段，通过 reversed() 实现倒序：
     */
    public static void demo4() {
        orders.stream().filter(order -> order.getTotalPrice() > 50)
                .sorted(Comparator.comparing(Order::getTotalPrice).reversed())
                .limit(5)
                .forEach(System.out::println);
    }

    /**
     * distinct
     * distinct 操作的作用是去重，类似 SQL 中的 distinct。比如下面的代码实现：
     * 查询去重后的下单用户。使用 map 从订单提取出购买用户，然后使用 distinct 去重。
     * 查询购买过的商品名。使用 flatMap+map 提取出订单中所有的商品名，然后使用 distinct 去重。
     */
    public static void demo5() {
        // 用户名
        System.out.println(orders.stream().map(Order::getCustomerName).distinct().collect(joining(",")));
        // 商品名
        System.out.println(orders.stream().flatMap(order -> order.getOrderItemList().stream()).map(OrderItem::getProductName).distinct().collect(joining(",")));
    }

    /**
     * skip & limit
     * kip 和 limit 操作用于分页，类似 MySQL 中的 limit。其中，skip 实现跳过一定的项，limit 用于限制项总数。比如下面的两段代码：
     * 按照下单时间排序，查询前 2 个订单的顾客姓名和下单时间；
     * 按照下单时间排序，查询第 3 和第 4 个订单的顾客姓名和下单时间。
     */
    public static void demo6() {
        orders.stream().sorted(Comparator.comparing(Order::getPlacedAt))
                .map(order -> order.getCustomerName() + "@" + order.getPlacedAt())
                .limit(2)
                .forEach(System.out::println);
        System.out.println("-----");
        orders.stream().sorted(Comparator.comparing(Order::getPlacedAt))
                .map(order -> order.getCustomerName() + "@" + order.getPlacedAt())
                .skip(2).limit(2)
                .forEach(System.out::println);
    }

    /**
     * collect
     * collect 是收集操作，对流进行终结（终止）操作，把流导出为我们需要的数据结构。
     * “终结”是指，导出后，无法再串联使用其他中间操作，比如 filter、map、flatmap、sorted、distinct、limit、skip。
     * 在 Stream 操作中，collect 是最复杂的终结操作，比较简单的终结操作还有 forEach、toArray、min、max、count、anyMatch 等，
     * 不再展开了，可以查询JDK 文档，搜索 terminal operation 或 intermediate operation。
     * 接下来，我通过 6 个案例，来演示下几种比较常用的 collect 操作：
     * 第一个案例，实现了字符串拼接操作，生成一定位数的随机字符串。
     * 第二个案例，通过 Collectors.toSet 静态方法收集为 Set 去重，得到去重后的下单用户，再通过 Collectors.joining 静态方法实现字符串拼接。
     * 第三个案例，通过 Collectors.toCollection 静态方法获得指定类型的集合，比如把 List转换为 LinkedList。
     * 第四个案例，通过 Collectors.toMap 静态方法将对象快速转换为 Map，Key 是订单 ID、Value 是下单用户名。
     * 第五个案例，通过 Collectors.toMap 静态方法将对象转换为 Map。
     * Key 是下单用户名，Value 是下单时间，一个用户可能多次下单，所以直接在这里进行了合并，只获取最近一次的下单时间。
     * 第六个案例，使用 Collectors.summingInt 方法对商品数量求和，再使用 Collectors.averagingInt 方法对结果求平均值，以统计所有订单平均购买的商品数量。
     */
    public static void demo7() {

        //生成一定位数的随机字符串
        System.out.println(new Random().ints(48, 122)
                .filter(i -> (i < 57 || i > 65) && (i < 90 || i > 97))
                .mapToObj(i -> (char) i)
                .limit(20)
                .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
                .toString());

        //所有下单的用户，使用toSet去重后实现字符串拼接
        System.out.println(orders.stream()
                .map(Order::getCustomerName).collect(toSet())
                .stream().collect(joining(",", "[", "]")));

        //用toCollection收集器指定集合类型
        System.out.println(orders.stream().limit(2).collect(toCollection(LinkedList::new)).getClass());

        //使用toMap获取订单ID+下单用户名的Map
        orders.stream()
                .collect(toMap(Order::getId, Order::getCustomerName))
                .entrySet().forEach(System.out::println);

        //使用toMap获取下单用户名+最近一次下单时间的Map
        orders.stream()
                .collect(toMap(Order::getCustomerName, Order::getPlacedAt, (x, y) -> x.isAfter(y) ? x : y))
                .entrySet().forEach(System.out::println);

        //订单平均购买的商品数量
        System.out.println(orders.stream().collect(averagingInt(order ->
                order.getOrderItemList().stream()
                        .collect(summingInt(OrderItem::getProductQuantity)))));
    }

    /**
     * groupBy
     * groupBy 是分组统计操作，类似 SQL 中的 group by 子句。
     * 它和后面介绍的 partitioningBy 都是特殊的收集器，同样也是终结操作。分组操作比较复杂，为帮你理解得更透彻，我准备了 8 个案例：
     *      第一个案例，按照用户名分组，使用 Collectors.counting 方法统计每个人的下单数量，再按照下单数量倒序输出。
     *      第二个案例，按照用户名分组，使用 Collectors.summingDouble 方法统计订单总金额，再按总金额倒序输出。
     *      第三个案例，按照用户名分组，使用两次 Collectors.summingInt 方法统计商品采购数量，再按总数量倒序输出。
     *      第四个案例，统计被采购最多的商品。先通过 flatMap 把订单转换为商品，然后把商品名作为 Key、Collectors.summingInt 作为 Value 分组统计采购数量，再按 Value 倒序获取第一个 Entry，最后查询 Key 就得到了售出最多的商品。
     *      第五个案例，同样统计采购最多的商品。相比第四个案例排序 Map 的方式，这次直接使用 Collectors.maxBy 收集器获得最大的 Entry。
     *      第六个案例，按照用户名分组，统计用户下的金额最高的订单。Key 是用户名，Value 是 Order，直接通过 Collectors.maxBy 方法拿到金额最高的订单，然后通过 collectingAndThen 实现 Optional.get 的内容提取，最后遍历 Key/Value 即可。
     *      第七个案例，根据下单年月分组统计订单 ID 列表。Key 是格式化成年月后的下单时间，Value 直接通过 Collectors.mapping 方法进行了转换，把订单列表转换为订单 ID 构成的 List。
     *      第八个案例，根据下单年月 + 用户名两次分组统计订单 ID 列表，相比上一个案例多了一次分组操作，第二次分组是按照用户名进行分组。
     */
    public static void demo8() {
        // 第一个案例，按照用户名分组，使用 Collectors.counting 方法统计每个人的下单数量，再按照下单数量倒序输出。
        System.out.println(orders.stream().collect(groupingBy(Order::getCustomerName, counting()))
                .entrySet().stream().sorted(Map.Entry.<String, Long>comparingByValue().reversed()).collect(toList()));

        // 第二个案例，按照用户名分组，使用 Collectors.summingDouble 方法统计订单总金额，再按总金额倒序输出。
        System.out.println(orders.stream().collect(groupingBy(Order::getCustomerName, summingDouble(Order::getTotalPrice)))
                .entrySet().stream().sorted(Map.Entry.<String, Double>comparingByValue().reversed()).collect(toList()));

        // 第三个案例，按照用户名分组，使用两次 Collectors.summingInt 方法统计商品采购数量，再按总数量倒序输出。
//        System.out.println(orders.stream().collect(groupingBy(Order::getCustomerName, summingInt(order -> order.getOrderItemList().stream()
//                .collect(summingInt(OrderItem::getProductQuantity)))))
//                .entrySet().stream().sorted(Map.Entry.<String, Integer>comparingByValue().reversed()).collect(toList()));
        System.out.println(orders.stream().collect(groupingBy(Order::getCustomerName, summingInt(order ->
                (Integer) order.getOrderItemList().stream().mapToInt(OrderItem::getProductQuantity).sum())))
                .entrySet().stream().sorted(Map.Entry.<String, Integer>comparingByValue().reversed()).collect(toList()));

        // 第四个案例，统计被采购最多的商品。先通过 flatMap 把订单转换为商品，
        // 然后把商品名作为 Key、Collectors.summingInt 作为 Value 分组统计采购数量，再按 Value 倒序获取第一个 Entry，最后查询 Key 就得到了售出最多的商品。
        orders.stream().flatMap(order -> order.getOrderItemList().stream())
                .collect(groupingBy(OrderItem::getProductName, summingInt(OrderItem::getProductQuantity)))
                .entrySet().stream().max(Map.Entry.comparingByValue()).ifPresent(System.out::println);
//        orders.stream().flatMap(order -> order.getOrderItemList().stream())
//                .collect(groupingBy(OrderItem::getProductName, summingInt(OrderItem::getProductQuantity)))
//                .entrySet().stream()
//                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
//                .map(Map.Entry::getKey)
//                .findFirst()
//                .ifPresent(System.out::println);

        // 第五个案例，同样统计采购最多的商品。相比第四个案例排序 Map 的方式，这次直接使用 Collectors.maxBy 收集器获得最大的 Entry。
        orders.stream().flatMap(order -> order.getOrderItemList().stream())
                .collect(groupingBy(OrderItem::getProductName, summingInt(OrderItem::getProductQuantity)))
                .entrySet().stream().collect(maxBy(Map.Entry.comparingByValue()))
                .ifPresent(System.out::println);

        // 第六个案例，按照用户名分组，统计用户下的金额最高的订单。
        // Key 是用户名，Value 是 Order，直接通过 Collectors.maxBy 方法拿到金额最高的订单，
        // 然后通过 collectingAndThen 实现 Optional.get 的内容提取，最后遍历 Key/Value 即可。
//        orders.stream().collect(groupingBy(Order::getCustomerName, collectingAndThen(maxBy(Comparator.comparingDouble(Order::getTotalPrice)), Optional::get)))
//                .forEach((k, v) -> System.out.println(k + "#" + v.getTotalPrice() + "@" + v.getPlacedAt()));
        orders.stream().collect(toMap(Order::getCustomerName, Function.identity(), BinaryOperator.maxBy(Comparator.comparingDouble(Order::getTotalPrice))))
                .forEach((k, v) -> System.out.println(k + "#" + v.getTotalPrice() + "@" + v.getPlacedAt()));

        // 第七个案例，根据下单年月分组统计订单 ID 列表。Key 是格式化成年月后的下单时间，Value 直接通过 Collectors.mapping 方法进行了转换，
        // 把订单列表转换为订单 ID 构成的 List。
        orders.stream().collect(
                groupingBy(order -> order.getPlacedAt().format(DateTimeFormatter.ofPattern("yyyyMM")), mapping(Order::getId, toList()))
        ).forEach((k, v) -> System.out.println(k + "-->" + v));

        // 第八个案例，根据下单年月 + 用户名两次分组统计订单 ID 列表，相比上一个案例多了一次分组操作，第二次分组是按照用户名进行分组。
        orders.stream().collect(
                groupingBy(order -> order.getPlacedAt().format(DateTimeFormatter.ofPattern("yyyyMM")),
                        groupingBy(Order::getCustomerName, mapping(Order::getId, toList()))))
                .forEach((k, v) -> System.out.println(k + "-->" + v));

        System.out.println(orders.stream().collect(
                groupingBy(order -> order.getPlacedAt().format(DateTimeFormatter.ofPattern("yyyyMM")),
                        groupingBy(order -> order.getCustomerName(), mapping(order -> order.getId(), toList())))));
    }
    /**
     * partitionBy
     *  partitioningBy 用于分区，分区是特殊的分组，只有 true 和 false 两组。
     *  比如，我们把用户按照是否下单进行分区，给 partitioningBy 方法传入一个 Predicate 作为数据分区的区分，输出是 Map<Boolean, List<T>>：
     *  扩展知识：
     *      anyMatch表示，判断的条件里，任意一个元素成功，返回true
     *      allMatch表示，判断条件里的元素，所有的都是，返回true
     *      noneMatch跟allMatch相反，判断条件里的元素，所有的都不是，返回true
     */
    public static void demo9() {
        //根据是否有下单记录进行分区
        Customer.getData().stream().collect(
                partitioningBy(customer ->
                        orders.stream().anyMatch(order -> order.getCustomerId().equals(customer.getId()))))
                .forEach((k, v) -> System.out.println(k + "-->" + v));
    }

    /**
     *思考与讨论
     *  1.使用 Stream 可以非常方便地对 List 做各种操作，那有没有什么办法可以实现在整个过程中观察数据变化呢？
     *  比如，我们进行 filter+map 操作，如何观察 filter 后 map 的原始数据呢？
     *  2.Collectors 类提供了很多现成的收集器，那我们有没有办法实现自定义的收集器呢？
     *  比如，实现一个 MostPopularCollector，来得到 List 中出现次数最多的元素，满足下面两个测试用例：
     */
    public static void demo10() {
        System.out.println(Stream.of(1, 1, 2, 2, 2, 3, 4, 5, 5).parallel().collect(new MostPopularCollector<>()).get() == 2);
        System.out.println(Stream.of('a', 'b', 'c', 'c', 'c', 'd').parallel().collect(new MostPopularCollector<>()).get() == 'c');
        System.out.println(Stream.of(1, 1, 2, 2, 2, 3, 4, 5, 5).parallel().collect(new MostPopularCollector2<>()).get() == 2);
        System.out.println(Stream.of('a', 'b', 'c', 'c', 'c', 'd').parallel().collect(new MostPopularCollector2<>()).get() == 'c');
        System.out.println(Stream.of(1, 1, 2, 2, 2, 3, 4, 5, 5).parallel().collect(new MostPopularCollector3<>()).get() == 2);
        System.out.println(Stream.of('a', 'b', 'c', 'c', 'c', 'd').parallel().collect(new MostPopularCollector3<>()).get() == 'c');
        System.out.println(Stream.of("hello", "world", "java", "nihao", "java", "java", "wold", "hello").parallel().collect(new MostPopularCollector<>()).orElse("").equals("java"));

        System.out.println(Stream.of(1, 1, 2, 2, 2, 3, 4, 5, 5).parallel().collect(MyCollectors.getCountMax()).get() == 2);

        System.out.println(Stream.of('a', 'b', 'c', 'c', 'c', 'd').parallel().collect(
                new MostPopularCollector4<>(
                    HashMap<Character, Integer>::new,
                    (acc, t) -> acc.compute(t, (key, val) -> val == null ? 1 : val + 1),
                    (m1, m2) -> {
                        m1.forEach((key, count) -> m2.put(key, m2.get(key) == null ? count : m2.get(key) + count));
                        return m2;
                    },
                    acc -> Optional.ofNullable(acc.entrySet()
                            .stream()
                            .max(Comparator.comparingInt(entry -> (int) entry.getValue()))
                            .get().getKey()),
                    MostPopularCollector4.CH_CONCURRENT
        )).get() == 'c');
        System.out.println(Stream.of('a', 'b', 'c', 'c', 'c', 'd').parallel().collect(
                new Collector<Character, Map<Character, Integer>, Optional<Character>>() {
                    @Override
                    public Supplier<Map<Character, Integer>> supplier() {
                        return HashMap::new;
                    }
                    @Override
                    public BiConsumer<Map<Character, Integer>, Character> accumulator() {
                        return (acc, t) ->
                                acc.compute(t, (key, val) -> val == null ? 1 : val + 1);
                    }
                    @Override
                    public BinaryOperator<Map<Character, Integer>> combiner() {
                        return (m1, m2) -> {
                            m1.forEach((key, count) -> m2.put(key, m2.get(key) == null ? count : m2.get(key) + count));
                            return m2;
                        };
                    }
                    @Override
                    public Function<Map<Character, Integer>, Optional<Character>> finisher() {
                        return acc -> Optional.ofNullable(acc.entrySet()
                                .stream()
                                .max(Comparator.comparingInt(entry -> (int) entry.getValue()))
                                .get().getKey());
                    }
                    @Override
                    public Set<Characteristics> characteristics() {
                        return Collections.unmodifiableSet(EnumSet.of(Characteristics.CONCURRENT));
                    }
                }).get() == 'c');


//        List<Integer> randomList = IntStream.rangeClosed(1, 100000000)
//                .mapToObj(i -> ThreadLocalRandom.current().nextInt(10)).collect(toList());
////        System.out.println(randomList);
//        randomList.stream().collect(Collectors.toMap(k -> k, x -> 1, Integer::sum))
//                .entrySet().forEach(System.out::println);
//        System.out.println(randomList.stream().parallel().collect(new MostPopularCollector<>()).orElse(0));
//        System.out.println(randomList.stream().parallel().collect(new MostPopularCollector2<>()).orElse(0));
//        System.out.println(randomList.stream().parallel().collect(new MostPopularCollector3<>()).orElse(0));

    }

    /*
     * Collectors：
     *  特性	        释义
     *  CONCURRENT	    表示结果容器A支持并发，如果指定该特征，则在并行计算时，多个线程对同一个结果进行汇聚(supplier只调用一次，combiner不会被调用)；
     *                  如果不指定，则在并行计算时，多个线程对不同的结果进行汇聚(supplier调用多次，combiner进行并行结果的合并)
     *  UNORDERED	    表示汇聚操作不需要保留流中元素的顺序，当结果容器无明显的顺序时设置
     *  IDENTITY_FINISH	当A类型与R类型相同时设置，此时finisher不执行，直接进行A到R的强制类型转换
     *
     */
}
