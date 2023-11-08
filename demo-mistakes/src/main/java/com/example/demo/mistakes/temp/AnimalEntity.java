package com.example.demo.mistakes.temp;

import lombok.Data;

/**
 * @author zhenghao
 * @description TODO
 * @date 2020/7/2 9:50
 */
@Data
public abstract class AnimalEntity {
    private String name;
    private Integer age;
    abstract void cry();
    abstract void sleep();
}
