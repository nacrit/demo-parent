package com.example.demo.mistakes.demo21.reflection.right;

import lombok.Data;

import java.math.BigDecimal;

/**
 * <p>
 * 支付接口
 * </p>
 *
 * @author zhenghao
 * @date 2020/9/10 16:28
 */
@BankAPI(url = "/bank/pay", desc = "支付接口")
@Data
public class PayAPI extends AbstractAPI {
    @BankAPIField(order = 1, type = "N", length = 20)
    private long userId;
    @BankAPIField(order = 2, type = "M", length = 10)
    private BigDecimal amount;
}