package com.example.demorabbitmq.demo2;

import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Random;

import static com.example.demorabbitmq.demo2.RabbitMqConfig.MY_BEAN_TOPIC_QUEUE_A;
import static com.example.demorabbitmq.demo2.RabbitMqConfig.MY_BEAN_TOPIC_QUEUE_B;

@Component
public class RabbitConsumer {
    Logger log = LoggerFactory.getLogger(this.getClass());

    // 主题队列A监听
    @RabbitListener(queues = MY_BEAN_TOPIC_QUEUE_A)
    public void handleTopicA(String msg, Message message, Channel channel) {
        //  如果手动ACK,消息会被监听消费,但是消息在队列中依旧存在,如果 未配置 acknowledge-mode 默认是会在消费完毕后自动ACK掉
        final long deliveryTag = message.getMessageProperties().getDeliveryTag();
        try {
            log.info("------------------队列A，手动ACK，接收消息：msg={}, message={}, deliveryTag={}", msg, message, deliveryTag);
            // 通知 MQ 消息已被成功消费,可以ACK了
            channel.basicAck(deliveryTag, false);
        } catch (IOException e) {
            try {
                // 处理失败,重新压入MQ
                channel.basicRecover();
            } catch (IOException e1) {
                log.warn("队列A异常-处理失败,重新压入MQ", e1);
            }
        }
    }

    // 主题队列A监听
    @RabbitListener(queues = MY_BEAN_TOPIC_QUEUE_B)
    public void handleTopicB(String msg, Message message, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag) {
        try {
            if (new Random().nextInt() % 2 == 0) {
                throw new RuntimeException("消费队列B失败..");
            }
            log.info("------------------队列B，手动ACK，接收消息：msg={}, message={}, deliveryTag={}", msg, message, deliveryTag);
            // 通知 MQ 消息已被成功消费,可以ACK了
            channel.basicAck(deliveryTag, false);
        } catch (IOException e) {
            try {
                // 处理失败,重新压入MQ
                channel.basicRecover();
            } catch (IOException e1) {
                log.warn("队列B异常-处理失败,重新压入MQ", e1);
            }
        }
    }

}