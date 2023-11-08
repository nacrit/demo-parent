package com.example.demo.mistakes.demo22.api_resposne;

import com.example.demo.mistakes.common.Utils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * <p>
 * CommonMistakesApplication
 * </p>
 *
 * @author zhenghao
 * @date 2020/9/14 10:42
 */
//@SpringBootApplication
public class CommonMistakesApplication {

    public static void main(String[] args) {
        Utils.loadPropertySource(CommonMistakesApplication.class, "config.properties");
        SpringApplication.run(CommonMistakesApplication.class, args);
    }
}