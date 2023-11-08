package com.example.demo.mistakes.demo04;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import javax.annotation.PostConstruct;
import java.util.concurrent.TimeUnit;

/**
 * @author zhenghao
 * @description 从源码角度分析下 Jedis 类到底属于哪种类型的 API
 *  我们先看一下涉及 TCP 连接的客户端 SDK，对外提供 API 的三种方式。在面对各种三方客户端的时候，
 *  只有先识别出其属于哪一种，才能理清楚使用方式。
 *          1.连接池和连接分离的 API：有一个 XXXPool 类负责连接池实现，先从其获得连接 XXXConnection，然后用获得的连接进行服务端请求，完成后使用者需要归还连接。
 *      通常，XXXPool 是线程安全的，可以并发获取和归还连接，而 XXXConnection 是非线程安全的。对应到连接池的结构示意图中，XXXPool 就是右边连接池那个框，
 *      左边的客户端是我们自己的代码。
 *          2.内部带有连接池的 API：对外提供一个 XXXClient 类，通过这个类可以直接进行服务端请求；这个类内部维护了连接池，SDK 使用者无需考虑连接的获取和归还问题。
 *      一般而言，XXXClient 是线程安全的。对应到连接池的结构示意图中，整个 API 就是蓝色框包裹的部分。
 *          3.非连接池的 API：一般命名为 XXXConnection，以区分其是基于连接池还是单连接的，而不建议命名为 XXXClient 或直接是 XXX。
 *      直接连接方式的 API 基于单一连接，每次使用都需要创建和断开连接，性能一般，且通常不是线程安全的。对应到连接池的结构示意图中，
 *      这种形式相当于没有右边连接池那个框，客户端直接连接服务端创建连接。
 *
 * @date 2020/6/29 17:43
 */
@Component
@Slf4j
public class JedisDemo {

    @PostConstruct
    public void init() {
        // 初始化reids的值
        try (Jedis jedis = new Jedis("127.0.0.1", 6379)) {
            jedis.auth("123456");
            Assert.isTrue("OK".equals(jedis.set("a", "1")), "set a = 1 return OK");
            Assert.isTrue("OK".equals(jedis.set("b", "2")), "set b = 2 return OK");
        }
        // 关闭连接池
        Runtime.getRuntime().addShutdownHook(new Thread(() -> jedisPool.close()));
    }

    /**
     * Jedis 继承了 BinaryJedis，BinaryJedis 中保存了单个 Client 的实例，Client 最终继承了 Connection，
     * Connection 中保存了单个 Socket 的实例，和 Socket 对应的两个读写流。因此，一个 Jedis 对应一个 Socket 连接。
     * inaryClient 封装了各种 Redis 命令，其最终会调用基类 Connection 的方法，使用 Protocol 类发送命令。
     * 看一下 Protocol 类的 sendCommand 方法的源码，可以发现其发送命令时是直接操作 RedisOutputStream 写入字节。
     * 我们在多线程环境下复用 Jedis 对象，其实就是在复用 RedisOutputStream。如果多个线程在执行操作，
     * 那么既无法确保整条命令以一个原子操作写入 Socket，也无法确保写入后、读取前没有其他数据写到远端
     */
    public void wrong() throws InterruptedException {
        try (Jedis jedis = new Jedis("127.0.0.1", 6379)) {
            jedis.auth("123456");
            new Thread(() -> {
                for (int i = 0; i < 1000; i++) {
                    String result = jedis.get("a");
                    if (!result.equals("1")) {
                        log.warn("Expect a to be 1 but found {}", result);
                        return;
                    }
                }
            }).start();
            new Thread(() -> {
                for (int i = 0; i < 1000; i++) {
                    String result = jedis.get("b");
                    if (!result.equals("2")) {
                        log.warn("Expect b to be 2 but found {}", result);
                        return;
                    }
                }
            }).start();
            TimeUnit.SECONDS.sleep(5);
        }
    }


    /**
     * 使用 Jedis 提供的另一个线程安全的类 JedisPool 来获得 Jedis 的实例。JedisPool 可以声明为 static 在多个线程之间共享，
     * 扮演连接池的角色。使用时，按需使用 try-with-resources 模式从 JedisPool 获得和归还 Jedis 实例。
     */
    private static final JedisPool jedisPool;
    static {
        JedisPoolConfig poolConfig = new JedisPoolConfig();
//        poolConfig.setMaxTotal(50);// 最大连接数，连接全部用完，进行等待
//        poolConfig.setMinIdle(10); // 最小空余数
//        poolConfig.setMaxIdle(30); // 最大空余数
        jedisPool = new JedisPool(poolConfig, "127.0.0.1", 6379, 100, "123456");
        // 分布式
//        JedisShardInfo jedisShardInfo1 = new JedisShardInfo("127.0.0.1", 6379);
//        jedisShardInfo1.setPassword("123456");
//        List<JedisShardInfo> list = new LinkedList<JedisShardInfo>();
//        list.add(jedisShardInfo1);
//        ShardedJedisPool shardedJedisPool = new ShardedJedisPool(poolConfig, list);
    }
    public void right() {
        new Thread(() -> {
            try (Jedis jedis = jedisPool.getResource()) {
                for (int i = 0; i < 1000; i++) {
                    String result = jedis.get("a");
                    if (!result.equals("1")) {
                        log.warn("Expect a to be 1 but found {}", result);
                        return;
                    }
                }
            }
        }).start();
        new Thread(() -> {
            try (Jedis jedis = jedisPool.getResource()) {
                for (int i = 0; i < 1000; i++) {
                    String result = jedis.get("b");
                    if (!result.equals("2")) {
                        log.warn("Expect b to be 2 but found {}", result);
                        return;
                    }
                }
            }
        }).start();
    }

}
