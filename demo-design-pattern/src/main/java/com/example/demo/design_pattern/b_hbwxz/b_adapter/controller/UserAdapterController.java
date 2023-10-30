package com.example.demo.design_pattern.b_hbwxz.b_adapter.controller;

import com.example.demo.design_pattern.b_hbwxz.a_base.pojo.UserInfo;
import com.example.demo.design_pattern.b_hbwxz.b_adapter.adapter.Login3rdAdapter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author mars
 * @description UserController
 * @date 2023/10/20 13:57
 */
@Slf4j
@RestController
@RequestMapping("/adapter")
public class UserAdapterController {
    @Resource
    private Login3rdAdapter login3rdAdapter;

    @PostMapping("/login")
    public String login(String account, String password) {
        log.info("[适配器方式] 用户登录 ..");
        return login3rdAdapter.login(account, password);
    }

    @PostMapping("/register")
    public String register(@RequestBody UserInfo userInfo) {
        log.info("[适配器方式] 用户注册 ..");
        return login3rdAdapter.register(userInfo);
    }

    @GetMapping("/gitee")
    public String gitee(String code, String state) {
        log.info("[适配器方式] gitee登录 ..");
        return login3rdAdapter.loginByGitee(code, state);
    }

}
