package com.example.demo.design_pattern.a_hfdp.z_apply.d2_annotation_reflaction.service_right;

import lombok.Data;

/**
 * 创建用户接口
 *
 * @author zhenghao
 * @date 2022/4/21 16:26
 */
@Data
@BankAPI(url = "/bank/createUser",  desc = "创建用户接口")
public class CreateUserAPI extends AbstractAPI {
    @BankAPIField(order = 1, length = 10, bankAPIFieldType = BankAPIFieldType.S)
    private String name;
    @BankAPIField(order = 2, length = 18, bankAPIFieldType = BankAPIFieldType.S)
    private String identity;
    @BankAPIField(order = 4, length = 11, bankAPIFieldType = BankAPIFieldType.S)
    String mobile;
    @BankAPIField(order = 3, length = 5, bankAPIFieldType = BankAPIFieldType.N)
    int age;
}
