package com.example.demo.springboot.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author mars
 * @description VueDatacontroller
 * @date 2024/6/28 10:21
 */
@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/vue3/data")
public class VueDataController {
    private static final List<User> USER_LIST = new ArrayList<>();

    static {
        try {
            initData();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    private static void initData() throws ParseException {
        USER_LIST.clear();
        String defaultPass = "123456";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        USER_LIST.add(new User(1L, "Alice", defaultPass, 18, sdf.parse("2024-01-01 00:00:00"), 1, "https://img2.baidu.com/it/u=3868198138,297404319&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=500"));
        USER_LIST.add(new User(2L, "Bob", defaultPass, 17, new Date(), 1, "https://img2.baidu.com/it/u=1706107271,3006035273&fm=253&fmt=auto&app=138&f=JPEG?w=400&h=400"));
        USER_LIST.add(new User(3L, "Charlie", defaultPass, 20, sdf.parse("2023-06-01 01:02:03"), 0, "https://q7.itc.cn/q_70/images03/20240423/6d236fae5c8f44ed9b60d977f32debb7.jpeg"));
    }

    @GetMapping
    public List<User> get(String name) {
        log.debug("[条件查询数据] name={}", name);
        return USER_LIST.stream()
                .filter(e -> name == null || e.getName().contains(name))
                .peek(e -> e.setPassword("***"))
                .collect(Collectors.toList());
    }

    @PostMapping
    public Boolean add(@RequestBody User user) {
        log.info("[添加数据] user={}", user);
        if (user.getId() == null) {
            user.setId(USER_LIST.stream().map(User::getId).max(Long::compareTo).orElse(1L));
        } else if (USER_LIST.stream().anyMatch(e -> e.getId().compareTo(user.getId()) == 0)) {
            log.warn("[添加数据失败] id已被使用 id={}", user.getId());
            return false;
        }
        return USER_LIST.add(user);
    }


    @PostMapping("/login")
    public Boolean login(@RequestBody User user) {
        log.info("[登陆用户] user={}", user);
        return USER_LIST.stream().anyMatch(e -> e.getName().equals(user.getName()) && e.getPassword().equals(user.getPassword()));
    }

    @PutMapping
    public Boolean modify(@RequestBody User user) {
        log.info("[修改数据] user={}", user);
        for (int i = 0; i < USER_LIST.size(); i++) {
            if (USER_LIST.get(i).id.equals(user.getId())) {
                USER_LIST.set(i, user);
                return true;
            }
        }
        log.warn("[修改数据失败] 未查询到对应id的数据 id={}", user.getId());
        return false;
    }


    @DeleteMapping
    public Boolean del(Long id) {
        log.info("[删除数据] id={}", id);
        return USER_LIST.removeIf(e -> e.getId().equals(id));
    }

    @PutMapping("/reset")
    public Boolean reset() throws ParseException {
        log.info("[重置数据] ..");
        initData();
        return true;
    }



    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class User {
        private Long id;
        private String name;
        private String password;
        private Integer age;
        private Date birthday;
        private Integer status;
        private String image;
    }
}
