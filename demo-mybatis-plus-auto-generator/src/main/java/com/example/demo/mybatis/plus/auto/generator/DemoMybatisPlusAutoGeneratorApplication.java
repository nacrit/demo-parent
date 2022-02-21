package com.example.demo.mybatis.plus.auto.generator;;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.demo.mybatis.plus.auto.generator.test2.mapper")
public class DemoMybatisPlusAutoGeneratorApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoMybatisPlusAutoGeneratorApplication.class, args);
    }


    /**
     * 注解
     * 介绍 MybatisPlus 注解包相关类详解(更多详细描述可点击查看源码注释)
     */
    public static void demo() {
        // @TableName 描述：表名注解
        /* @TableId 描述：主键注解
            IdType
                AUTO	数据库ID自增
                NONE	无状态,该类型为未设置主键类型(注解里等于跟随全局,全局里约等于 INPUT)
                INPUT	insert前自行set主键值
                ASSIGN_ID	分配ID(主键类型为Number(Long和Integer)或String)(since 3.3.0),使用接口IdentifierGenerator的方法nextId(默认实现类为DefaultIdentifierGenerator雪花算法)
                ASSIGN_UUID	分配UUID,主键类型为String(since 3.3.0),使用接口IdentifierGenerator的方法nextUUID(默认default方法)
                ID_WORKER	分布式全局唯一ID 长整型类型(please use ASSIGN_ID)
                UUID 32位UUID字符串(please use ASSIGN_UUID)
                ID_WORKER_STR	分布式全局唯一ID 字符串类型(please use ASSIGN_ID)
         */
        // @TableField 字段注解(非主键)
        // @Version  描述：乐观锁注解、标记 @Verison 在字段上
        // @EnumValue 描述：通枚举类注解(注解在枚举字段上)
        // @TableLogic 描述：表字段逻辑处理注解（逻辑删除） value:逻辑未删除值 delval:逻辑删除值
        // @SqlParser 描述：租户注解,支持method上以及mapper接口上
        // @KeySequence 描述：序列主键策略 oracle    属性：value、resultMap
    }

}
