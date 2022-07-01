package com.nacrt.demo.jdknew.collection.map;

import org.junit.Test;

import java.util.HashMap;
import java.util.TreeMap;

public class TreeMapTest {
    @Test
    public void testException() {
        HashMap<Object, Object> hm = new HashMap<>();
        hm.put("1", 1);
        hm.put(2, 2);
        hm.put('3', 3);
        System.out.println("hm = " + hm);

        // TreeMap的key类型必须固定
        TreeMap<Object, Object> treeMap = new TreeMap<>();
        treeMap.put("1", 1);
        treeMap.put(2, 2);
        treeMap.put('3', 3);
        // Exception in thread "main" java.lang.ClassCastException: java.lang.String cannot be cast to java.lang.Integer
        System.out.println("treeMap = " + treeMap);

        TreeMap<Object, String> tm = new TreeMap<>();
        tm.put(5, "1");
        tm.put(10.2, "2");
        tm.put(8.1f, "3");
        // Exception in thread "main" java.lang.ClassCastException: java.lang.Integer cannot be cast to java.lang.Double
        System.out.println("tm = " + tm);
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
