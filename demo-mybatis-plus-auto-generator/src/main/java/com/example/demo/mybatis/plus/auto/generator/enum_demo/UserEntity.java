package com.example.demo.mybatis.plus.auto.generator.enum_demo;

/**
 * @author zhenghao
 * @description 枚举测试实体
 * @date 2020/6/12 11:01
 */
public class UserEntity {
    /**
     * 名字
     * 数据库字段: name varchar(20)
     */
    private String name;

    /**
     * 年龄，IEnum接口的枚举处理
     * 数据库字段：age INT(3)
     */
    private AgeEnum age;


    /**
     * 年级，原生枚举（带{@link com.baomidou.mybatisplus.annotation.EnumValue}):
     * 数据库字段：grade INT(2)
     */
    private GradeEnum grade;
}
