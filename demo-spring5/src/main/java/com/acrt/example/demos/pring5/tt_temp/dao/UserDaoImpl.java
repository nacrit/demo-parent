package com.acrt.example.demos.pring5.tt_temp.dao;

import org.springframework.stereotype.Repository;

/**
 * <p>
 * UserDaoImpl
 * </p>
 *
 * @author zhenghao
 * @date 2021/1/13 17:50
 */
@Repository("userDao")
public class UserDaoImpl implements UserDao {
    @Override
    public void sayHello() {
        System.out.println("hello world ..");
    }
}
