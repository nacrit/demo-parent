package com.example.demo.mybatis.dao;
import com.example.demo.mybatis.model.DemoMybatis;
import org.apache.ibatis.annotations.Param;

public interface DemoMybatisMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DemoMybatis record);

    int insertSelective(DemoMybatis record);

    DemoMybatis selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DemoMybatis record);

    int updateByPrimaryKey(DemoMybatis record);
    int tempUpdate(@Param("id") Integer id,
                   @Param("name")  String name);
}