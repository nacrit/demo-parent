package com.example.demo.design_pattern.a_hfdp.z_apply.d1_factory_template.service_right;

import com.example.demo.design_pattern.a_hfdp.z_apply.d1_factory_template.dao.Db;
import com.example.demo.design_pattern.a_hfdp.z_apply.d1_factory_template.model.Item;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * VIP 用户的购物车逻辑
 *
 * @author zhenghao
 * @date 2022/4/21 15:10
 */
@Service("VipUserCart")
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
