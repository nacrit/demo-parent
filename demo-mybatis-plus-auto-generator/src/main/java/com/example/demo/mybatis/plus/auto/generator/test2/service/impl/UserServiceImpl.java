package com.example.demo.mybatis.plus.auto.generator.test2.service.impl;

import com.example.demo.mybatis.plus.auto.generator.test2.entity.User;
import com.example.demo.mybatis.plus.auto.generator.test2.mapper.UserMapper;
import com.example.demo.mybatis.plus.auto.generator.test2.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author anchorite
 * @since 2020-06-11
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
