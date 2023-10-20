package com.example.demo.design_pattern.a_head_first_design_patterns.o_interator;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;

public class Main {
    public static void main(String[] args) {
        // 以ArrayList的迭代器为例
        // 容器角色：AbstractList
        // 具体容器角色：ArrayList
        AbstractList<String> list = new ArrayList<>();
        list.add("hello");
        list.add("world");
        list.add("java");

        //这个便是负责创建具体迭代器角色的工厂方法
        Iterator<String> iter = list.iterator();

        // 迭代器角色：Iterator
        // 具体迭代器角色：AbstractList#Itr implements Iterator<E> 作为内部类
        while (iter.hasNext()) {
            String next = iter.next();
            System.out.println(next);
            if ("world".equals(next)) {
                iter.remove();
            }
        }
        System.out.println("----------------------------------");
        list.add("newWorld");
        int size = list.size();
        for (int i = 0; i < size; i++) {
            String next = list.get(i);
            if ("hello".equals(next)) {
                list.remove(i);
                size = list.size();
            }
            System.out.println(next);
        }
        System.out.println("-------------------------------");
        list.add("newHello");
//        for (String next : list) {
//            if ("java".equals(next)) {
//                list.remove(next);
//            }
//            System.out.println(next);
//        }

        // 具体迭代器角色：ListItr
        ListIterator<String> iter2 = list.listIterator();
        while (iter2.hasNext()) {
            String next = iter2.next();
            System.out.println(iter2.nextIndex() + "-->" + next);
            if ("java".equals(next)) {
                iter2.remove();
                iter2.add("newJava");
            }
        }
        System.out.println(list);
    }
}
