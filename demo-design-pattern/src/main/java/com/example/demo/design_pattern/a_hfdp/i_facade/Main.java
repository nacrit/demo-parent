package com.example.demo.design_pattern.a_hfdp.i_facade;

import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        // 客户端调用
        List<Map<String, String>> list = JDBCUtils.query("select * from tree_path", JDBCUtils.getTableFileNames("tree_path"));
        list.forEach(System.out::println);
    }
}
