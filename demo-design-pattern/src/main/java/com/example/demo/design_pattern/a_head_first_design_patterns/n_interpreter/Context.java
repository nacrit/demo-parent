package com.example.demo.design_pattern.a_head_first_design_patterns.n_interpreter;

import java.util.HashMap;
import java.util.Map;

//上下文（环境）角色，使用 HashMap 来存储变量对应的数值
public class Context {
    private Map<Variable, Integer> valueMap = new HashMap<>();

    public void addValue(Variable x, int y) {
        Integer yi = y;
        valueMap.put(x, yi);
    }

    public int LookupValue(Variable x) {
        return valueMap.get(x);
    }
}