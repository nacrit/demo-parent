package com.example.demo.mybatis.plus.auto.generator.enum_demo;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;

/**
 * @author zhenghao
 * @description 使用 @EnumValue 注解枚举属性
 * @date 2020/6/12 10:57
 */
@Getter
public enum GradeEnum {
    PRIMARY(1, "小学"),  SECONDARY(2, "中学"),  HIGH(3, "高中");

    GradeEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }
    @EnumValue//标记数据库存的值是code
    private final int code;
    private final String desc;
    //。。。
}