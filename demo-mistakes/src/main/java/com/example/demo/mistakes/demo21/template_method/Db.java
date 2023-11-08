package com.example.demo.mistakes.demo21.template_method;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class Db {
    private static Map<Long, BigDecimal> items = new HashMap<>();

    static {
        items.put(1L, new BigDecimal("10"));
        items.put(2L, new BigDecimal("20"));
    }

    public static BigDecimal getItemPrice(long id) {
        return items.get(id);
    }

    public static String getUserCategory(long userId) {

        if (userId == 1L) return "Normal";
        if (userId == 2L) return "Vip";
        if (userId == 3L) return "Internal";
        return "Normal";
    }

    /**
     * 购买两件以上相同商品，第三件开始享受一定折扣
     * @param userId 用户id
     * @return 折扣
     */
    public static int getUserCouponPercent(long userId) {
        return 90;
    }
}
