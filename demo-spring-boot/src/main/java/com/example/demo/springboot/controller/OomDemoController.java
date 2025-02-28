package com.example.demo.springboot.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author mars
 * @description OomDemoController
 * @date 2024/7/19 17:05
 */
@Slf4j
@RestController
@RequestMapping("/oom-demo")
public class OomDemoController {
    @GetMapping
    public int get(HttpServletRequest req) {
        log.info("get .. req={}", req);
        List<Object> list = new ArrayList<>();
        while (list.size() < 2_000_000_000) {
            list.add(new OomDemo(UUID.randomUUID().toString()));
            if (list.size() % 99999 == 0) {
                log.info("list.size={}", list.size());
            }
        }
        return list.size();
    }

    @AllArgsConstructor
    static class OomDemo {
        private String ss;
    }

}
