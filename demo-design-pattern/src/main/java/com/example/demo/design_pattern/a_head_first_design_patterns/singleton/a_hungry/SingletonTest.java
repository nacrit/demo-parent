package com.example.demo.design_pattern.a_head_first_design_patterns.singleton.a_hungry;

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

}
