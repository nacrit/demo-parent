package com.nacrt.demo.jdknew.jdk8.g_enum;

import java.util.stream.IntStream;

import static com.nacrt.demo.jdknew.jdk8.g_enum.DemoEnum.FAIL;
import static com.nacrt.demo.jdknew.jdk8.g_enum.DemoEnum.SUCCESS;

/**
 * @author mars
 * @description EnumTest
 * @date 2023/10/30 16:29
 */
public class EnumTest {
    public static void main(String[] args) {
        IntStream.rangeClosed(0, 0)
                .parallel()
                .forEach(i -> {
                    System.out.println("SUCCESS = " + SUCCESS);
                    System.out.println("code = " + SUCCESS.getCode());
                    System.out.println("msg = " + SUCCESS.getMsg());
                    System.out.println("IBaseEnum.valueOf(200) = " + IBaseEnum.valueOf(DemoEnum.class, 200));
                    System.out.println("FAIL = " + FAIL);
                    System.out.println("code = " + FAIL.getCode());
                    System.out.println("msg = " + FAIL.getMsg());
                    System.out.println("----------------------");
                    System.out.println("open = " + Demo2Enum.OPEN);
                    System.out.println("code = " + Demo2Enum.OPEN.getCode());
                    System.out.println("msg = " + Demo2Enum.OPEN.getMsg());
                    System.out.println("close = " + Demo2Enum.CLOSE);
                    System.out.println("code = " + Demo2Enum.CLOSE.getCode());
                    System.out.println("msg = " + Demo2Enum.CLOSE.getMsg());
                    System.out.println("hello = " + Demo2Enum.HELLO);
                    System.out.println("code = " + Demo2Enum.HELLO.getCode());
                    System.out.println("msg = " + Demo2Enum.HELLO.getMsg());
//                    System.out.println("-------------------------");
//                    DemoClass demoClass = new DemoClass("test", "demoClass");
//                    System.out.println("demoClass = " + demoClass);
//                    System.out.println("code = " + demoClass.getCode());
//                    System.out.println("msg = " + demoClass.getMsg());
//                    System.out.println("IBaseEnum.valueOf(200) = " + IBaseEnum.valueOf(DemoClass.class, "demoClass"));
                });
    }
}
