package com.example.demo.mistakes.temp;

import lombok.Data;

/**
 * @author zhenghao
 * @description TODO
 * @date 2020/7/2 9:50
 */
@Data
public class CatEntity extends AnimalEntity {

    @Override
    void cry() {
        System.out.println("喵喵喵 。。。");
    }

    @Override
    void sleep() {
        System.out.println("猫睡觉。。。");
    }
}
