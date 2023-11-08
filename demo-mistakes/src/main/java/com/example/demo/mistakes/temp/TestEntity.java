package com.example.demo.mistakes.temp;

import lombok.Data;

import java.util.List;

/**
 * @author zhenghao
 * @description TODO
 * @date 2020/7/2 9:20
 */
@Data
public class TestEntity<T> {
    private List<T> data;

}
