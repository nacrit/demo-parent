package com.example.demo.design_pattern.z_apply.d1_factory_template.service_right;

import com.example.demo.design_pattern.z_apply.d1_factory_template.model.Item;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * 内部用户购物车
 *
 * @author zhenghao
 * @date 2022/4/21 15:10
 */
@Service("InternalUserCart")
public class InternalUserCart extends AbstractCart {
    @Override
    protected void processCouponPrice(long userId, Item item) {
        item.setCouponPrice(BigDecimal.ZERO);
    }

    @Override
    protected void processDeliveryPrice(long userId, Item item) {
        item.setDeliveryPrice(BigDecimal.ZERO);
    }
}
