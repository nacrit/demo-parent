package com.acrt.example.demos.pring5.tt_temp.service;

import com.acrt.example.demos.pring5.tt_temp.dao.UserDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * UserService
 * </p>
 *
 * @author zhenghao
 * @date 2021/1/13 17:14
 */
@Service
public class UserService {
    /**
     * Resource：
     *  1.指定name和type只指定name 都按name装配，找不到抛异常
     *  2.指定type 都按类型装配，找不到或找到多个抛异常
     *  3.都不指定，默认先按名称，找不到再按类型，否则抛异常
     * Autowired：按类型装配，找不到或找到多个抛异常
     *  可与@Qualifier配合使用，按名称装配
     */
    @Resource
//    @Autowired(required = false)
//    @Qualifier("userDao1")
    private UserDao userDao;

    public void sayHello() {
        userDao.sayHello();
    }
}
