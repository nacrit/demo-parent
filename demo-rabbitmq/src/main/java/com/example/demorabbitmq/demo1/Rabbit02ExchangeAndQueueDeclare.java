package com.example.demorabbitmq.demo1;

import com.rabbitmq.client.*;
import org.junit.Before;
import org.junit.Test;
import org.springframework.amqp.core.ExchangeTypes;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

/**
 * ## 交换机：exchange的定义 <br />
 * Exchange.DeclareOk exchangeDeclare(String exchange, String type, boolean durable, boolean autoDelete, boolean internal,Map<String, Object> arguments） throws IOException； <br />
 * - exchange：定义交换机名称 <br />
 * - type：交换机四种type(direct、topic、fanout、headers) <br />
 * - durable：持久化 <br />
 * - autoDelete：孤立交换机 会被删除 <br />
 * - internal：内部交换机（至少接受二手消息） <br />
 * - arguments：里边可以存放一些东西，如AE交换机 <br />
 * <p>
 * ---------------- <br />
 * <p>
 * ## 队列：queue的定义 <br />
 * Queue.DeclareOk queueDeclare(String queue,boolean durable,boolean exclusive,boolean autoDelete,Map<String,Object> arguments) throws IOException; <br />
 * queue 名称 <br />
 * durable 持久化 <br />
 * exclusive：排他的queue，首次连接断开会自动删除 <br />
 * autoDelete： 孤立自动删除 <br />
 * arguments：arguments的配置，如：死信队列+ttl。 <br />
 * ---------------- <br />
 * <p>
 * ## 发消息设置 <br />
 * channel.basicPublish (exchangeName, routingKey, mandatory,MessageProperties.PERSISTENT_TEXT_PLAIN, messageBodyBytes);  <br />
 * mandatory:true时，如果交换机没有根据routingKey找到对应的queue，那么交换机可以把这条消息返回给生产者。添加addReturnListener-returnCallback。 <br />
 * new AMQP.BasicProperties.Builder() <br />
 * •contentType("text/plain") 消息内容类型 <br />
 * •deliveryMode(2) 2为消息持久化 <br />
 * •priority(1) 优先级 <br />
 * •userId("hidden") 用户id <br />
 * .build(); <br />
 *
 * ---------------- <br />
 * <p>
 * ## 备份交换机 <br />
 * 如果不适用我们的returnListener机制，我们可以将路由失败的信息转发到备份交换机，成称备份交换机为AE. <br />
 * 加了备份交换机 returnListener机制 会失效 <br />
 * 正常交换机绑定："alternate-exchange" --> "备份交换机名称" <br />
 *
 */
public class Rabbit02ExchangeAndQueueDeclare {
    private ConnectionFactory cf;
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
            String routingKey = "direct.q";
            String aeExchange = "aeExchange";
            String aeQueue = "aeQueue";
            Map<String, Object> arguments = Collections.singletonMap("alternate-exchange", "aeExchange");
            // 定义交换机
            channel.exchangeDeclare(exchangeName, ExchangeTypes.DIRECT, true, false, false, arguments);
            // 定义队列
            String myQueue1 = "myQueue1";
            Map<String, Object> args = Collections.singletonMap("x-max-length", 1); // 队列最多保存2条消息
//            channel.queueDeclare("limited_queue", true, false, false, args);
            channel.queueDeclare(myQueue1, true, false, false, args);
            channel.queueBind(myQueue1, exchangeName, routingKey);

            // 备份交换机
            channel.exchangeDeclare(aeExchange, ExchangeTypes.FANOUT, true, false, true, null);
            channel.queueDeclare(aeQueue, true, false, false, null);
            channel.queueBind(aeQueue, aeExchange, "");

            // 处理失败的消息在此执行，需要 发消息（basicPublish）参数 mandatory为true时生效
            channel.addReturnListener((replyCode, replyText, exchange, rk, properties, body) -> {
                System.out.println("-+-+-+-+ ReturnListener 消息处理失败 = " + new String(body));
            });
            channel.addReturnListener(returnMessage -> {
                System.out.println("-+-+-+-+ ReturnCallback 消息处理失败 = " + new String(returnMessage.getBody()));
            });

            channel.confirmSelect();
            channel.addConfirmListener(new ConfirmListener() {

                /**
                 * Broker成功处理消息（写入磁盘或路由到队列）
                 * 典型原因：
                 *  - 消息持久化完成
                 *  - 消息被路由到至少一个队列
                 *  - 消息被内存队列接收
                 */
                @Override
                public void handleAck(long deliveryTag, boolean multiple) throws IOException {
                    System.out.println("------------- handleAck, deliveryTag=" + deliveryTag + ", 批量确认=" + multiple);
                }

                /**
                 * Broker无法处理消息
                 * 典型原因：
                 *  - Broker内部错误
                 *  - 队列已满且无法入队
                 *  - 消息被拒绝（权限/校验问题）
                 */
                @Override
                public void handleNack(long deliveryTag, boolean multiple) throws IOException {
                    System.out.println("============== handleNack, deliveryTag=" + deliveryTag + ", 批量拒绝=" + multiple);
                }
            });

            // 注册2个消费者
            consumer(channel, myQueue1, 1111111);
            consumer(channel, myQueue1, 2222222);
            consumer(channel, aeQueue,  3333333);

            // 发消息
//            IntStream.range(0, 5000).parallel().forEach(i -> {
//                String msg = "msg-" + i;
//                System.out.println(msg);
//                try {
//                    channel.basicPublish(exchangeName, routingKey + (i % 2 == 0 ? "" : i), true, new AMQP.BasicProperties.Builder()
//                            .contentType("text/plain")
//                            .deliveryMode(2) // 消息持久化
//                            .userId("guest")
//                            .build(), msg.getBytes());
//                } catch (IOException e) {
//                    throw new RuntimeException(e);
//                }
//            });

//            for (int i = 0; i < 10; i++) {
//                String msg = "msg-" + i;
//                System.out.println(msg);
//                channel.basicPublish(exchangeName, routingKey + (i % 2 == 0 ? "" : i), true, new AMQP.BasicProperties.Builder()
//                        .contentType("text/plain")
//                        .deliveryMode(2) // 消息持久化
//                        .userId("guest")
//                        .build(), msg.getBytes());
//            }

            sleep(3);
            System.out.println("++++++++++++++++++ direct, count=" + count.get()); // 20
            System.in.read();
        }
    }


    /**
     * 消费者
     */
    private void consumer(Channel channel, String qn, int idx) throws IOException {
        channel.basicConsume(qn, false, new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                long deliveryTag = envelope.getDeliveryTag();
                System.out.println("--------- " + ExchangeTypes.DIRECT + "-->" + qn + "-" + idx + ".. msg=" + new String(body) + ", deliveryTag=" + deliveryTag);
//                sleep(0.01);
                channel.basicAck(deliveryTag, true);
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
