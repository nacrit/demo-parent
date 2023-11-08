package com.example.demo.mistakes.demo18.reflection;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author zhenghao
 * @description 反射测试
 * @date 2020/7/19 17:14
 */
@Slf4j
public class ReflectionIssueApplication {
    private void age(int age) {
        log.info("int age = {}", age);
    }

    private void age(Integer age) {
        log.info("Integer age = {}", age);
    }

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
//        ReflectionIssueApplication application = new ReflectionIssueApplication();
//        application.age(36);
//        application.age(Integer.valueOf("36"));
//
//        System.out.println("----------------------------------");
//        application.getClass().getDeclaredMethod("age", Integer.TYPE).invoke(application, Integer.valueOf("36"));
//        application.getClass().getDeclaredMethod("age", Integer.TYPE).invoke(application, 36);
//
//        System.out.println("----------------------------------");
//        application.getClass().getDeclaredMethod("age", Integer.class).invoke(application, Integer.valueOf("36"));
//        application.getClass().getDeclaredMethod("age", Integer.class).invoke(application, 36);


//        Child1 child1 = new Child1();
//        Arrays.stream(child1.getClass().getMethods())
//                .filter(method -> method.getName().equals("setValue"))
//                .forEach(method -> {
//                    try {
//                        method.invoke(child1, "test");
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                });
//        System.out.println(child1.toString());

        Child2 child2 = new Child2();
//        Arrays.stream(child2.getClass().getMethods())
//                .filter(method -> method.getName().equals("setValue"))
//                .forEach(method -> {
//                    try {
//                        method.invoke(child2, "test");
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                });

        // 解决方式
        Arrays.stream(child2.getClass().getDeclaredMethods())
                .filter(method -> method.getName().equals("setValue") && !method.isBridge())
                .findFirst().ifPresent(method -> {
            try {
                method.invoke(child2, "test");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        System.out.println(child2.toString());

    }
}

class Parent<T> {
    //用于记录value更新的次数，模拟日志记录的逻辑
    AtomicInteger updateCount = new AtomicInteger();
    private T value;
    //重写toString，输出值和值更新次数
    @Override
    public String toString() {
        return String.format("value: %s updateCount: %d", value, updateCount.get());
    }
    //设置值
    public void setValue(T value) {
        System.out.println("Parent.setValue called");
        this.value = value;
        updateCount.incrementAndGet();
    }
}

class Child1 extends Parent {
//    @Override
//    public void setValue(Object value) {
//        System.out.println("Child1.setValue called Override");
//        super.setValue(value);
//    }

    public void setValue(String value) {
        System.out.println("Child1.setValue called");
        super.setValue(value);
    }
}


class Child2 extends Parent<String> {
    @Override
    public void setValue(String value) {
        System.out.println("Child2.setValue called");
        super.setValue(value);
    }
}
