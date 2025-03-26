package com.example.demorabbitmq.demo1;

import com.rabbitmq.client.*;
import org.junit.Before;
import org.junit.Test;
import org.springframework.amqp.core.ExchangeTypes;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 使用 rabbit 发送消息 及 接收消息： <br />
 * 四种交换机类型：direct、topic、fanout、header <br />
 * - Direct：精确匹配routing key <br />
 * - Topic：支持通配符模式匹配 <br />
 * - Fanout：广播到所有绑定队列 <br />
 * - Headers：通过消息头键值对匹配（基本不再用） <br />
 * ------ <br />
 * ** 特性： <br />
 * * 交换机路由机制：交换机会根据类型和绑定规则，将消息路由到符合条件的所有队列。 <br />
 * * 队列独立性：每个队列的消息消费是独立的，不同队列中的相同消息会被不同消费者处理。 <br />
 */
public class Rabbit01Exchange {
    private ConnectionFactory cf;
    private final String myQueue1 = "myQueue1";
    private final String myQueue2 = "myQueue2";
    private final AtomicInteger count = new AtomicInteger(0);

    @Before
    public void before() {
        cf = new ConnectionFactory();
        cf.setHost("localhost");
        cf.setPort(5672);
        cf.setVirtualHost("/");
        cf.setUsername("guest");
        cf.setPassword("guest");
    }

    /**
     * directEx --> q1,q2
     */
    @Test
    public void directTest() throws IOException, TimeoutException {
        try (Connection connection = cf.newConnection()) {
            Channel channel = connection.createChannel();
            String exchangeName = "myDirectEx";
            String routingKey = "direct.q"; // 精准匹配才能收到消息
            exAndQ(channel, exchangeName, ExchangeTypes.DIRECT, routingKey, myQueue1, myQueue2);
            // 注册4个消费者
            consumer(channel, myQueue1, 1, ExchangeTypes.DIRECT);
            consumer(channel, myQueue1, 2, ExchangeTypes.DIRECT);
            consumer(channel, myQueue2, 3, ExchangeTypes.DIRECT);
            consumer(channel, myQueue2, 4, ExchangeTypes.DIRECT);
            // 发消息
            sendMsg("+++++++++ direct, msg=", channel, exchangeName, routingKey);
            sleep(5);
            System.out.println("++++++++++++++++++ direct, count=" + count.get()); // 20
        }
    }

    /**
     * topicEx1 --> q1,q2
     * topicEx2 --> q3,q4
     */
    @Test
    public void topicExchangeTest() throws IOException, TimeoutException {
        try (Connection connection = cf.newConnection()) {
            Channel channel = connection.createChannel();
            String exchangeName1 = "myTopicExchange1";
            String exchangeName2 = "myTopicExchange2";
            String routingKey1 = "topic.q1.666"; // 支持通配符模式匹配
            String routingKey2 = "topic.q2.666"; // 支持通配符模式匹配
            String myQueue3 = "myQueue3";
            String myQueue4 = "myQueue4";
            exAndQ(channel, exchangeName1, ExchangeTypes.TOPIC, "topic.q1.*", myQueue1, myQueue2);
            exAndQ(channel, exchangeName2, ExchangeTypes.TOPIC, "topic.q2.*", myQueue3, myQueue4);
            consumer(channel, myQueue1, 1, ExchangeTypes.TOPIC);
            consumer(channel, myQueue1, 2, ExchangeTypes.TOPIC);
            consumer(channel, myQueue2, 3, ExchangeTypes.TOPIC);
            consumer(channel, myQueue2, 4, ExchangeTypes.TOPIC);
            consumer(channel, myQueue3, 5, ExchangeTypes.TOPIC);
            consumer(channel, myQueue3, 6, ExchangeTypes.TOPIC);
            consumer(channel, myQueue4, 7, ExchangeTypes.TOPIC);
            consumer(channel, myQueue4, 8, ExchangeTypes.TOPIC);
            sendMsg("+++++++++ topic1, msg=", channel, exchangeName1, routingKey1);
            sendMsg("+++++++++ topic2, msg=", channel, exchangeName2, routingKey2);
            sleep(5);
            System.out.println("++++++++++++++++++ topic, count=" + count.get()); // 20
        }
    }

    /**
     * fanoutEx --> q1,q2
     */
    @Test
    public void fanoutExchangeTest() throws IOException, TimeoutException {
        try (Connection connection = cf.newConnection()) {
            Channel channel = connection.createChannel();
            String exchangeName = "myFanoutExchange";
            String routingKey = "fanout.q"; // 这个可以不设置，设置了也无效
            exAndQ(channel, exchangeName, ExchangeTypes.FANOUT, routingKey, myQueue1, myQueue2);
            consumer(channel, myQueue1, 1, ExchangeTypes.FANOUT);
            consumer(channel, myQueue1, 2, ExchangeTypes.FANOUT);
            consumer(channel, myQueue2, 3, ExchangeTypes.FANOUT);
            consumer(channel, myQueue2, 4, ExchangeTypes.FANOUT);
            // routingKey是无效的（什么key都一样），绑定了就会转发消息
            sendMsg("+++++++++ fanout, msg=", channel, exchangeName, "random");
            sleep(5);
            System.out.println("++++++++++++++++++ fanout, count=" + count.get()); // 20

        }
    }

    /**
     * headersEx --> q1,q2
     */
    @Test
    public void headerExchangeTest() throws IOException, TimeoutException {
        String type = ExchangeTypes.HEADERS;
        try (Connection connection = cf.newConnection()) {
            Channel channel = connection.createChannel();
            String exchangeName = "myHeaderExchange";
            String routingKey = type + ".q";
            // 定义交换机
            channel.exchangeDeclare(exchangeName, type, false, false, null);
            channel.queueDeclare(myQueue1, false, false, false, null);
            channel.queueDeclare(myQueue2, false, false, false, null);
            // 队列绑定参数
            Map<String, Object> bindingArgs = new HashMap<>();
            bindingArgs.put("x-match", "all"); // 必须同时匹配以下所有
            bindingArgs.put("format", "pdf");
            bindingArgs.put("type", "report");
            channel.queueBind(myQueue1, exchangeName, routingKey, bindingArgs);
            channel.queueBind(myQueue2, exchangeName, routingKey, bindingArgs);


            // 消费者
            consumer(channel, myQueue1, 1, type);
            consumer(channel, myQueue1, 2, type);
            consumer(channel, myQueue2, 3, type);
            consumer(channel, myQueue2, 4, type);


            // 设置消息头
            AMQP.BasicProperties.Builder propsBuilder = new AMQP.BasicProperties.Builder();
            Map<String, Object> headers = new HashMap<>();
            headers.put("format", "pdf");
            headers.put("type", "report");
            propsBuilder.headers(headers);
            // 发消息
            for (int i = 0; i < 5; i++) {
                String msg = "+++++++++ " + type + ", msg=" + "hw" + i;
                System.out.println(msg);
                channel.basicPublish(exchangeName, "random" + i, propsBuilder.build(),
                        msg.getBytes());
            }
            sleep(5);
            System.out.println("++++++++++++++++++ " + type + ", count=" + count.get()); // 20

        }
    }

    /**
     * 批量发送消息
     */
    private static void sendMsg(String x, Channel channel, String exchangeName, String routingKey) throws IOException {
        for (int i = 0; i < 5; i++) {
            String msg = x + "hw" + i;
            System.out.println(msg);
            channel.basicPublish(exchangeName, routingKey, null, msg.getBytes());
        }
    }


    /**
     * 定义 exchange、queue、并绑定关系
     */
    private void exAndQ(Channel channel, String exchangeName, String type, String routingKey, String... qns) throws IOException {
        // 定义交换机
        channel.exchangeDeclare(exchangeName, type, false, false, null);
        // 定义队列
        for (String qn : qns) {
            channel.queueDeclare(qn, false, false, false, null);
            channel.queueBind(qn, exchangeName, routingKey);
        }
    }


    /**
     * 消费者
     */
    private void consumer(Channel channel, String qn, int idx, String type) throws IOException {
        channel.basicConsume(qn, true, new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) {
                System.out.println("--------- " + type + "-->" + qn + "-" + idx + ".. msg=" + new String(body));
                sleep(0.1);
                count.incrementAndGet();
            }
        });
    }

    // 睡眠
    private void sleep(double seconds) {
        try {
            Thread.sleep((long) (1000 * seconds));
        } catch (InterruptedException e) {
            e.printStackTrace(System.err);
        }
    }


}
