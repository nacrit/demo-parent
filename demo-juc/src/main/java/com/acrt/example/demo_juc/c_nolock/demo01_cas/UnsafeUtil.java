package com.acrt.example.demo_juc.c_nolock.demo01_cas;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * UnsafeUtil
 *
 * @author zhenghao
 * @date 2022/4/6 14:53
 */
public class UnsafeUtil {
    private static final Unsafe UNSAFE;
    static {
        Unsafe tempUnsafe = null;
        try {
            Field theUnsafeField = Unsafe.class.getDeclaredField("theUnsafe");
            theUnsafeField.setAccessible(true);
            tempUnsafe = (Unsafe) theUnsafeField.get(null);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        UNSAFE = tempUnsafe;
    }

    public static Unsafe getUnsafe() {
        return UNSAFE;
    }

    public static void main(String[] args) {
        System.out.println(getUnsafe());
    }
}
