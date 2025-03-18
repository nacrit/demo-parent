package com.example.demo.design_pattern.a_head_first_design_patterns.singleton.b_lazy;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

public class SingletonTest {
    private void exec(Runnable task) {
        IntStream.range(0, 20)
                .parallel()
                .forEach(i -> {
                    for (int j = 0; j < 10; j++) {
                        task.run();
                    }
                });
    }

    @Test
    void test1() {
        exec(() -> Assertions.assertEquals(Singleton.getInstance(), Singleton.getInstance()));
    }


    @Test
    void test2() {
        exec(() -> Assertions.assertEquals(Singleton2.getInstance(), Singleton2.getInstance()));
    }

    @Test
    void test3() {
        exec(() -> Assertions.assertEquals(Singleton3.INSTANCE, Singleton3.INSTANCE));
    }

    @Test
    void test4() {
        exec(() -> {
            Assertions.assertEquals(Singleton4.getInstance(Singleton.class.getName()), Singleton4.getInstance(Singleton.class.getName()));
            Assertions.assertEquals(Singleton4.getInstance(Singleton2.class.getName()), Singleton4.getInstance(Singleton2.class.getName()));
            // 枚举类型无法通过反射创建对象
//            Assertions.assertEquals(Singleton4.getInstance(Singleton3.class.getName()), Singleton4.getInstance(Singleton3.class.getName()));
            Assertions.assertEquals(Singleton4.getInstance(Singleton4.class.getName()), Singleton4.getInstance(Singleton4.class.getName()));
        });
    }

}
