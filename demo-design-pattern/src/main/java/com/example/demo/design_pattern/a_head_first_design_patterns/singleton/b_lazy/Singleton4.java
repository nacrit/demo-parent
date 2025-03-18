package com.example.demo.design_pattern.a_head_first_design_patterns.singleton.b_lazy;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 通过线程安全集合实现
 */
public class Singleton4 {
    private static final Map<String, Object> SINGLETON_MAP = new ConcurrentHashMap<>();
    public static Object getInstance(String className) {
        return SINGLETON_MAP.computeIfAbsent(className, key -> {
            try {
//                Thread.sleep(500);
                Class<?> clazz = Class.forName(key);
                Constructor<?> constructor = clazz.getDeclaredConstructor();
                constructor.setAccessible(true);
                return constructor.newInstance();
            } catch (InstantiationException | IllegalAccessException | ClassNotFoundException | NoSuchMethodException |
                     InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
