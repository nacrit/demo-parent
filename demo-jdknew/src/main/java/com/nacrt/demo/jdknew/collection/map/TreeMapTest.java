package com.nacrt.demo.jdknew.collection.map;

import org.junit.Test;

import java.util.Comparator;
import java.util.HashMap;
import java.util.TreeMap;

public class TreeMapTest {
    @Test
    public void testException() {
        // 错误示范1：key未实现Comparable接口
        TreeMap<Object, Object> tm = new TreeMap<>();
        //java.lang.ClassCastException: java.lang.Object cannot be cast to java.lang.Comparable
        tm.put(new Object(), null);

        // 错误示范2：key的类型不一致问题
        TreeMap<Object, Object> tm2 = new TreeMap<>();
        tm2.put("1", null);
        // Exception in thread "main" java.lang.ClassCastException: java.lang.String cannot be cast to java.lang.Integer
        tm2.put(2, null);

        // 正确演示1：构造方法传入Comparable实现类
        TreeMap<Object, Object> tm3 = new TreeMap<>(Comparator.comparingInt(Object::hashCode));
        tm3.put(new Object(), null);
        tm3.put(new Object(), null);
        System.out.println("tm3 = " + tm3);

        // 正确演示2：
        TreeMap<Object, Object> tm4 = new TreeMap<>();
        tm4.put("1", null);
        tm4.put("2", null);
        System.out.println("tm4 = " + tm4);
    }

    @Test
    public void testPut() {
        TreeMap<Integer, Object> treeMap = new TreeMap<>();
        treeMap.put(4, "");
        treeMap.put(1, "");
        treeMap.put(3, "");
        treeMap.put(2, "");
        System.out.println("treeMap = " + treeMap);
    }
}
