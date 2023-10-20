package com.example.demo.design_pattern.a_hfdp.z_apply.d1_factory_template.dao;

import java.math.BigDecimal;

/**
 * Db
 *
 * @author zhenghao
 * @date 2022/4/21 14:35
 */
public class Db {

    // 商品id，价格
    public static BigDecimal getItemPrice(Long id) {
        return BigDecimal.valueOf(id).multiply(BigDecimal.TEN);
    }

    // 折扣比例
    public static int getUserCouponPercent(long userId) {
        return 90;
    }

    public static String getUserCategory(int userId) {
        if (userId == 1L) return "Normal";
        if (userId == 2L) return "Vip";
        if (userId == 3L) return "Internal";
        return "Normal";
    }
}
