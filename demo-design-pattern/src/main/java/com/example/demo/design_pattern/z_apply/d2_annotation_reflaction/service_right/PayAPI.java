package com.example.demo.design_pattern.z_apply.d2_annotation_reflaction.service_right;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 支付接口
 *
 * @author zhenghao
 * @date 2022/4/21 16:40
 */
@Data
@BankAPI(url = "/bank/pay", desc = "支付接口")
public class PayAPI extends AbstractAPI {
    @BankAPIField(order = 1, length = 20, bankAPIFieldType = BankAPIFieldType.N)
    private Long userId;
    @BankAPIField(order = 2, length = 10, bankAPIFieldType = BankAPIFieldType.M)
    private BigDecimal amount;
}
