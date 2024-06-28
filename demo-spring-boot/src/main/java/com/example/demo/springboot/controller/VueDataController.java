package com.example.demo.springboot.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author mars
 * @description VueDatacontroller
 * @date 2024/6/28 10:21
 */
@CrossOrigin
@RestController
@RequestMapping("/vue3/data")
public class VueDataController {
    private static final List<User> USER_LIST = new ArrayList<>();
    static {
        USER_LIST.add(new User(1L, "Alice", 18, new Date(), 1, "https://img2.baidu.com/it/u=3868198138,297404319&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=500"));
        USER_LIST.add(new User(2L, "Bob", 17, new Date(), 1, "https://img2.baidu.com/it/u=1706107271,3006035273&fm=253&fmt=auto&app=138&f=JPEG?w=400&h=400"));
        USER_LIST.add(new User(3L, "Charlie", 20, new Date(), 0, "https://q7.itc.cn/q_70/images03/20240423/6d236fae5c8f44ed9b60d977f32debb7.jpeg"));
    }

    @GetMapping
    public List<User> get(String name) {
        return USER_LIST.stream()
                .filter(e -> name == null || e.getName().contains(name))
                .collect(Collectors.toList());
    }

    @PostMapping
    public Boolean add(@RequestBody User user) {
        if (user.getId() == null) return false;
        return USER_LIST.add(user);
    }

    @PutMapping
    public Boolean modify(@RequestBody User user) {
        for (int i = 0; i < USER_LIST.size(); i++) {
            if (USER_LIST.get(i).id.equals(user.getId())) {
                USER_LIST.set(i, user);
                return true;
            }
        }
        return false;
    }


    @DeleteMapping
    public Boolean del(Long id) {
        return USER_LIST.removeIf(e -> e.getId().equals(id));
    }


    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class User {
        private Long id;
        private String name;
        private Integer age;
        private Date birthday;
        private Integer status;
        private String image;
    }
}
