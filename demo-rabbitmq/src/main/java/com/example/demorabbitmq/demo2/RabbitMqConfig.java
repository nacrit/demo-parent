package com.example.demorabbitmq.demo2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {
    Logger log = LoggerFactory.getLogger(this.getClass());
    public static final String MY_BEAN_TOPIC_EXCHANGE = "my.bean.topic.exchange";
    public static final String MY_BEAN_TOPIC_QUEUE_A = "my.bean.topic.queueA";
    public static final String MY_BEAN_TOPIC_QUEUE_B = "my.bean.topic.queueB";

    @Bean
    public RabbitTemplate rabbitTemplate(CachingConnectionFactory connectionFactory) {
        connectionFactory.setPublisherConfirmType(CachingConnectionFactory.ConfirmType.CORRELATED);
        connectionFactory.setPublisherReturns(true);
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMandatory(true);
        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) ->
                log.info("消息发送成功:correlationData({}),ack({}),cause({})", correlationData, ack, cause));
        rabbitTemplate.setReturnCallback((message, replyCode, replyText, exchange, routingKey) ->
                log.info("消息丢失:exchange({}),route({}),replyCode({}),replyText({}),message:{}", exchange, routingKey, replyCode, replyText, message));
        return rabbitTemplate;
    }

    @Bean
    public TopicExchange myBeanTopicExchange() {
        return new TopicExchange(MY_BEAN_TOPIC_EXCHANGE, true, false, null);
    }

    @Bean
    public Queue myBeanTopicQueueA() {
        return new Queue(MY_BEAN_TOPIC_QUEUE_A, true, false, false, null);
    }

    @Bean
    public Queue myBeanTopicQueueB() {
        return new Queue(MY_BEAN_TOPIC_QUEUE_B, true, false, false, null);
    }

    @Bean
    public Binding myBeanTopicBindingA() {
        return BindingBuilder.bind(myBeanTopicQueueA()).to(myBeanTopicExchange()).with("my.bean.order.*");
    }

    @Bean
    public Binding myBeanTopicBindingB() {
        return BindingBuilder.bind(myBeanTopicQueueB()).to(myBeanTopicExchange()).with("my.bean.user.#");
    }
}