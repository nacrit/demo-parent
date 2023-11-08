package com.example.demo.mistakes.iocaop;

import lombok.Data;

/**
 * <p>
 * UserEntity
 * </p>
 *
 * @author zhenghao
 * @date 2020/8/18 16:26
 */
//@Entity(name = "user")
@Data
public class UserEntity {
//    @Id
//    @GeneratedValue(strategy = AUTO)
    private Long id;
    private String name;
    public UserEntity() {
    }

    public UserEntity(String name) {
        this.name = name;
    }
}
