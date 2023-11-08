package com.example.demo.mistakes.demo31;

import java.util.concurrent.ThreadLocalRandom;
import java.util.function.*;

/**
 * @author zhenghao
 * @description Lambda表达式Demo
 * @date 2020/6/17 9:29
 */
public class LambdaDemo {

    public static void main(String[] args) {
        demo3();
//        demo4();
    }

    // 匿名类和lambda表达式创建一个线程
    public static void demo1() {
        //匿名类
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("hello1");
            }
        }).start();
        //Lambda表达式
        new Thread(() -> System.out.println("hello2")).start();
    }

    /**
     * java.util.function 包中定义了各种函数式接口。比如，用于提供数据的 Supplier 接口，就只有一个 get 抽象方法，没有任何入参、有一个返回值：
     */
    public static void demo2() {
        //使用Lambda表达式提供Supplier接口实现，返回OK字符串
        Supplier<String> stringSupplier = ()->"OK";
        //使用方法引用提供Supplier接口实现，返回空字符串
        Supplier<String> supplier = String::new;
    }

    /**
     * 使用 Lambda 表达式或方法引用来构建函数的例子
     * Predicate：提供一个参数返回一个布尔值，通过and或or连接条件
     * Consumer：传一个参数消费数据不返回，通过andThen组合两个Consumer
     * Supplier：产生数据
     * Function：传一个参数得出结果并返回,BiFunction传两个参数得出结果并返回
     * BinaryOperator：输入两个参数，输出结果，并且参数和结果类型相同
     */
    public static void demo3() {
        //Predicate接口是输入一个参数，返回布尔值。我们通过and方法组合两个Predicate条件，判断是否值大于0并且是偶数
        Predicate<Integer> positiveNumber = i -> i > 0;
        Predicate<Integer> evenNumber = i -> i % 2 == 0;
        System.out.println(positiveNumber.and(evenNumber).test(2));

        //Consumer接口是消费一个数据。我们通过andThen方法组合调用两个Consumer，输出两行abcdefg
        Consumer<String> println = System.out::println;
        println.andThen(println).accept("abcdefg");

        //Function接口是输入一个数据，计算后输出一个数据。我们先把字符串转换为大写，然后通过andThen组合另一个Function实现字符串拼接
        Function<String, String> upperCase = String::toUpperCase;
        Function<String, String> duplicate = s -> s.concat(s);
        BiFunction<String, String, String> concat = String::concat;
        System.out.println(concat.apply(upperCase.apply("test"), upperCase.apply("test")));
        System.out.println(upperCase.andThen(duplicate).apply("test"));

        //Supplier是提供一个数据的接口。这里我们实现获取一个随机数
        Supplier<Integer> random = ()->ThreadLocalRandom.current().nextInt();
        System.out.println(random.get());

        //BinaryOperator是输入两个同类型参数，输出一个同类型参数的接口。这里我们通过方法引用获得一个整数加法操作，通过Lambda表达式定义一个减法操作，然后依次调用
        BinaryOperator<Integer> add = Integer::sum;
        BinaryOperator<Integer> subtraction = (a, b) -> a - b;
        System.out.println(subtraction.apply(add.apply(1, 2), 3));
    }

    private static void demo4() {
        for (int j = 0; j < 20; j++) {
            if (isPrime(j))
                System.out.println(j);
        }
    }

    private static boolean isPrime(Integer m) {
//        return test(m, n -> {
//            if (n < 2) return false;
//            for (int i = 2; i * i <= n; i++)
//                if (n % i == 0) return false;
//            return true;
//        });
        Predicate<Integer> tPredicate = n -> {
            if (n < 2) return false;
            for (int i = 2; i * i <= n; i++)
                if (n % i == 0) return false;
            return true;
        };
        return tPredicate.test(m);
    }

    private static boolean test(Integer n, Predicate<Integer> predicate) {
        return predicate.test(n);
    }


    /*
    常用函数式接口：
        BiConsumer<T,U>
            Represents an operation that accepts two input arguments and returns no result.
        BiFunction<T,U,R>
            Represents a function that accepts two arguments and produces a result.
        BinaryOperator<T>
            Represents an operation upon two operands of the same type, producing a result of the same type as the operands.
        BiPredicate<T,U>
            Represents a predicate (boolean-valued function) of two arguments.
        BooleanSupplier
            Represents a supplier of boolean-valued results.
        Consumer<T>
            Represents an operation that accepts a single input argument and returns no result.
        DoubleBinaryOperator
            Represents an operation upon two double-valued operands and producing a double-valued result.
        DoubleConsumer
            Represents an operation that accepts a single double-valued argument and returns no result.
        DoubleFunction<R>
            Represents a function that accepts a double-valued argument and produces a result.
        DoublePredicate
            Represents a predicate (boolean-valued function) of one double-valued argument.
        DoubleSupplier
            Represents a supplier of double-valued results.
        DoubleToIntFunction
            Represents a function that accepts a double-valued argument and produces an int-valued result.
        DoubleToLongFunction
            Represents a function that accepts a double-valued argument and produces a long-valued result.
        DoubleUnaryOperator
            Represents an operation on a single double-valued operand that produces a double-valued result.
        Function<T,R>
            Represents a function that accepts one argument and produces a result.
        IntBinaryOperator
            Represents an operation upon two int-valued operands and producing an int-valued result.
        IntConsumer
            Represents an operation that accepts a single int-valued argument and returns no result.
        IntFunction<R>
            Represents a function that accepts an int-valued argument and produces a result.
        IntPredicate
            Represents a predicate (boolean-valued function) of one int-valued argument.
        IntSupplier
            Represents a supplier of int-valued results.
        IntToDoubleFunction
            Represents a function that accepts an int-valued argument and produces a double-valued result.
        IntToLongFunction
            Represents a function that accepts an int-valued argument and produces a long-valued result.
        IntUnaryOperator
            Represents an operation on a single int-valued operand that produces an int-valued result.
        LongBinaryOperator
            Represents an operation upon two long-valued operands and producing a long-valued result.
        LongConsumer
            Represents an operation that accepts a single long-valued argument and returns no result.
        LongFunction<R>
            Represents a function that accepts a long-valued argument and produces a result.
        LongPredicate
            Represents a predicate (boolean-valued function) of one long-valued argument.
        LongSupplier
            Represents a supplier of long-valued results.
        LongToDoubleFunction
            Represents a function that accepts a long-valued argument and produces a double-valued result.
        LongToIntFunction
            Represents a function that accepts a long-valued argument and produces an int-valued result.
        LongUnaryOperator
            Represents an operation on a single long-valued operand that produces a long-valued result.
        ObjDoubleConsumer<T>
            Represents an operation that accepts an object-valued and a double-valued argument, and returns no result.
        ObjIntConsumer<T>
            Represents an operation that accepts an object-valued and a int-valued argument, and returns no result.
        ObjLongConsumer<T>
            Represents an operation that accepts an object-valued and a long-valued argument, and returns no result.
        Predicate<T>
            Represents a predicate (boolean-valued function) of one argument.
        Supplier<T>
            Represents a supplier of results.
        ToDoubleBiFunction<T,U>
            Represents a function that accepts two arguments and produces a double-valued result.
        ToDoubleFunction<T>
            Represents a function that produces a double-valued result.
        ToIntBiFunction<T,U>
            Represents a function that accepts two arguments and produces an int-valued result.
        ToIntFunction<T>
            Represents a function that produces an int-valued result.
        ToLongBiFunction<T,U>
            Represents a function that accepts two arguments and produces a long-valued result.
        ToLongFunction<T>
            Represents a function that produces a long-valued result.
        UnaryOperator<T>
            Represents an operation on a single operand that produces a result of the same type as its operand.
     */
}
