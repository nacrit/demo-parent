package com.example.demo.mybatis.plus.model;

import lombok.Data;

/**
 * @author zhenghao
 * @description 用户实体
 * @date 2020/6/10 14:38
 */
@Data
public class User {
    private Long id;
    private String name;
    private Integer age;
    private String email;
}