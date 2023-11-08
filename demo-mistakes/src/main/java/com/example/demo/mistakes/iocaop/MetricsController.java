package com.example.demo.mistakes.iocaop;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * MetricsController
 * </p>
 *
 * @author zhenghao
 * @date 2020/8/18 16:24
 */

@Slf4j
@RestController //自动进行监控
@RequestMapping("/metrics-test")
public class MetricsController {
    @Autowired
    private UserService userService;
    @GetMapping("/transaction")
    public int transaction(@RequestParam("name") String name) {
        try {
            userService.createUser(new UserEntity(name));
        } catch (Exception ex) {
            log.error("create user failed because {}", ex.getMessage());
        }
        return userService.getUserCount(name);
    }
}
