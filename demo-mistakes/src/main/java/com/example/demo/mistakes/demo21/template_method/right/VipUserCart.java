package com.example.demo.mistakes.demo21.template_method.right;

import com.example.demo.mistakes.demo21.template_method.Db;
import com.example.demo.mistakes.demo21.template_method.Item;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * <p>
 * TODO
 * </p>
 *
 * @author zhenghao
 * @date 2020/9/10 16:06
 */
@Service(value = "VipUserCart")
public class VipUserCart extends NormalUserCart {

    @Override
    protected void processCouponPrice(long userId, Item item) {
        if (item.getQuantity() > 2) {
            item.setCouponPrice(item.getPrice()
                    .multiply(BigDecimal.valueOf(100 - Db.getUserCouponPercent(userId)).multiply(new BigDecimal("0.01")))
                    .multiply(BigDecimal.valueOf(item.getQuantity() - 2)));
        } else {
            item.setCouponPrice(BigDecimal.ZERO);
        }
    }
}
