package com.example.demo.mistakes.demo21.reflection.right;

import lombok.Data;

/**
 * <p>
 * CreateUserAPI
 * </p>
 *
 * @author zhenghao
 * @date 2020/9/10 16:22
 */
@BankAPI(url = "/bank/createUser", desc = "创建用户接口")
@Data
public class CreateUserAPI extends AbstractAPI {
    @BankAPIField(order = 1, type = "S", length = 10)
    private String name;
    @BankAPIField(order = 2, type = "S", length = 18)
    private String identity;
    @BankAPIField(order = 4, type = "S", length = 11)
    private String mobile;
    @BankAPIField(order = 3, type = "N", length = 5)
    private int age;
}
