package com.nacrt.demo.jdknew.jdk7;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Demo
 *
 * @author zhenghao
 * @date 2022/3/30 21:56
 */
public class Demo {
    public static void main(String[] args) {
        // 1.2进制表示
        int a = 0b001;
        // 2.数值类型用下划线
        int b = 10_123_321;
        // 3.try-with-resource
        try (FileInputStream fis = new FileInputStream("")) {
            System.out.println("fis.available() = " + fis.available());
            ;
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 4.catch优化
        try {
            System.out.println();
        } catch (NullPointerException | NoSuchMethodError e) {
            System.out.println("e = " + e);
        }
        // 5.泛型推断
        List<String> list = new ArrayList<>();
        // 6.switch case 支持String类型
        switch (UUID.randomUUID().toString()) {
            case "1":
                System.out.println(1);
                break;
            case "2":
                System.out.println(2);
                break;
            default:
        }
    }
}
