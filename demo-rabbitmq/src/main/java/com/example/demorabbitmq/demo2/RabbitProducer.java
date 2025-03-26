package com.example.demorabbitmq.demo2;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

import static com.example.demorabbitmq.demo2.RabbitMqConfig.MY_BEAN_TOPIC_EXCHANGE;

@Component
public class RabbitProducer {
    @Resource
    private RabbitTemplate rabbitTemplate;

    // 主题交换机发送
    public void sendTopic(String routingKey, Object data) {
        rabbitTemplate.convertAndSend(MY_BEAN_TOPIC_EXCHANGE, routingKey, data);
    }
}