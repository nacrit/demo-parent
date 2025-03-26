package com.example.demorabbitmq.demo2;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Random;

@RestController
@RequestMapping("/mq")
public class TestController {
    @Resource
    private RabbitProducer producer;

    @GetMapping("/topic")
    public String sendTopic(String msg) {
        producer.sendTopic(new Random().nextInt() % 2 == 0 ? "my.bean.user.created" : "my.bean.order.completed", msg);
        return "sent";
    }
}