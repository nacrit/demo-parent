package com.example.demo.mistakes.common;

import lombok.extern.slf4j.Slf4j;

import java.util.Properties;

/**
 * <p>
 * Utils
 * </p>
 *
 * @author zhenghao
 * @date 2020/9/14 10:44
 */

@Slf4j
public class Utils {

    public static void loadPropertySource(Class<?> clazz, String fileName) {
        try {
            Properties p = new Properties();
            p.load(clazz.getResourceAsStream(fileName));
            p.forEach((k, v) -> {
                log.info("{}={}", k, v);
                System.setProperty(k.toString(), v.toString());
            });
        } catch (Exception e) {
//            throw new RuntimeException(e);
        }
    }
}