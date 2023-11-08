package com.example.demo.mistakes.demo31.precondition;
import java.util.stream.IntStream;

/**
 * @author zhenghao
 * @description 调试lambda demo
 * @date 2020/6/18 17:06
 */
public class DebugDemo {
    public static void main(String[] args) {
        // 单击“ 跟踪当前流链”按钮 跟踪当前流链按钮(调试框最后一个图标-/-)
        IntStream.iterate(1, n -> n + 1)
                .skip(Integer.parseInt(args[0]))
                .limit(Integer.parseInt(args[1]))
                .filter(DebugDemo::isPrime)
                .forEach(System.out::println);
//        StopWatch stopWatch = new StopWatch();
//        stopWatch.start("isPrime");
//        IntStream.rangeClosed(1, 100000).parallel().forEach(DebugDemo::isPrime);
//        stopWatch.stop();
//        stopWatch.start("isPrime2");
//        IntStream.rangeClosed(1, 100000).parallel().forEach(DebugDemo::isPrime2);
//        stopWatch.stop();
//        System.out.println(stopWatch.prettyPrint());
//        final long count = IntStream.rangeClosed(1, 100000).parallel()
//                .filter(i -> isPrime(i) ^ isPrime2(i))
//                .count();
//        System.out.println(count);
    }

    private static boolean isPrime(int x) {
        if (x < 2) {
            return false;
        }
        for (int i = 2; i * i <= x; i++) {
            if (x % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static boolean isPrime2(int n){
        if (n <= 3) {
            return n > 1;
        }
        for(int i = 2; i < n; i++){
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static boolean isPrime3(int num) {
        if (num <= 3) {
            return num > 1;
        }
        // 不在6的倍数两侧的一定不是质数
        if (num % 6 != 1 && num % 6 != 5) {
            return false;
        }
        int sqrt = (int) Math.sqrt(num);
        for (int i = 5; i <= sqrt; i += 6) {
            if (num % i == 0 || num % (i + 2) == 0) {
                return false;
            }
        }
        return true;
    }

}
