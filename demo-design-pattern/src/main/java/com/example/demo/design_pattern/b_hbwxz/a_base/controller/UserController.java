package com.example.demo.design_pattern.b_hbwxz.a_base.controller;

import com.example.demo.design_pattern.b_hbwxz.a_base.pojo.UserInfo;
import com.example.demo.design_pattern.b_hbwxz.a_base.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author mars
 * @description UserController
 * @date 2023/10/20 13:57
 */
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public String login(String account, String password) {
        return userService.login(account, password);
    }

    @PostMapping("/register")
    public String register(@RequestBody UserInfo userInfo) {
        return userService.register(userInfo);
    }


}
