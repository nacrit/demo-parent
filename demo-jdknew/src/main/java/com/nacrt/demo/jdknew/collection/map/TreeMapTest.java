package com.nacrt.demo.jdknew.collection.map;

import java.util.TreeMap;

public class TreeMapTest {
    static transient int i = 1;

    public static void main(String[] args) throws InterruptedException {
        TreeMap<Object, Object> treeMap = new TreeMap<>();
        treeMap.put("1", 1);
        treeMap.put(2, 2);
        treeMap.put('3', 3);
        System.out.println("treeMap = " + treeMap);

    }
}
