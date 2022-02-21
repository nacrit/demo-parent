package com.example.demo.mybatis.plus.auto.generator.enum_demo;

import com.baomidou.mybatisplus.core.enums.IEnum;
import lombok.Getter;

/**
 * @author zhenghao
 * @description 枚举属性，实现 IEnum 接口如下
 * @date 2020/6/12 10:58
 */
@Getter
public enum AgeEnum implements IEnum<Integer> {
    ONE(1, "一岁"),
    TWO(2, "二岁"),
    THREE(3, "三岁");

    private int value;
    private String desc;
    AgeEnum(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }
    @Override
    public Integer getValue() {
        return value;
    }
}
