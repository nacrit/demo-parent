package com.example.demo.mybatis.dao;

import org.apache.ibatis.annotations.Param;

public interface TempMapper {
    int tempUpdate(@Param("id") Integer id,
                   @Param("name") String name);
}