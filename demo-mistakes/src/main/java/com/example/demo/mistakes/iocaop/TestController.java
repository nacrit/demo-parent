package com.example.demo.mistakes.iocaop;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * TestController
 * </p>
 *
 * @author zhenghao
 * @date 2020/8/17 9:41
 */
@RestController
@RequestMapping("/ioc-aop")
@Slf4j
public class TestController {
    @Autowired
    List<SayService> sayServiceList;

    @GetMapping("/test")
    public void test() {
        log.info("====================, service size:" + sayServiceList.size());
        sayServiceList.forEach(SayService::say);
    }


    @Autowired
    private ApplicationContext applicationContext;

    @GetMapping("/test2")
    public void test2() {
        applicationContext.getBeansOfType(SayService.class).values().forEach(SayService::say);
    }
}
