package com.example.demo.design_pattern.b_hbwxz.a_base.service;

import com.example.demo.design_pattern.b_hbwxz.a_base.pojo.UserInfo;
import com.example.demo.design_pattern.b_hbwxz.a_base.repo.UserRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author mars
 * @description UserService
 * @date 2023/10/20 13:42
 */
@Service
public class UserService {
    @Resource
    private UserRepository userRepository;

    /**
     * 注册
     */
    public String register(UserInfo userInfo) {
        if (checkUserExists(userInfo.getUserName())) {
            throw new RuntimeException("账号已被注册");
        }
//        userInfo.setCreateDate(new Date());
        userRepository.save(userInfo);
        return "注册成功";
    }

    public boolean checkUserExists(String userName) {
        return userRepository.findByUserName(userName) != null;
    }

    /**
     * 登录
     */
    public String login(String account, String password) {
        UserInfo userInfo = userRepository.findByUserNameAndUserPassword(account, password);
        if (userInfo == null) {
            return "账户或密码错误";
        }
        return "登录成功";
    }
}
