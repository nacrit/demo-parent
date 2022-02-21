package com.example.demo.mybatis.plus.dao;

import com.example.demo.mybatis.plus.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTest {
    @Resource
    private UserMapper userMapper;

    @Test
    public void testSelect() {
        System.out.println(("----- selectAll method test ------"));
        List<User> userList = userMapper.selectList(null);
        Assert.assertNotNull(userList);
        userList.forEach(System.out::println);
    }
    @Test
    public void testInsert() {
        System.out.println(("----- Insert method test ------"));
        User user = new User();
        user.setAge(20);
        user.setEmail("123456@qq.com");
        user.setName("张一二");
        int count = userMapper.insert(user);
        Assert.assertNotEquals(count, 0);
    }


    @Test
    public void testUpdate() {
        System.out.println(("----- Update method test ------"));
        User user = userMapper.selectById(1426721613125275650L);
        user.setName("ZhangSan");
        user.setAge(18);
        int count = userMapper.updateById(user);
        Assert.assertNotEquals(count, 0);
    }


}