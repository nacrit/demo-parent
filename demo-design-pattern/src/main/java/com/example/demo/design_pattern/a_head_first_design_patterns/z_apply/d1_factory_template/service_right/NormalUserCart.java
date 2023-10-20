package com.example.demo.design_pattern.a_head_first_design_patterns.z_apply.d1_factory_template.service_right;

import com.example.demo.design_pattern.a_head_first_design_patterns.z_apply.d1_factory_template.model.Item;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * 普通用户购物车处理
 *
 * @author zhenghao
 * @date 2022/4/21 15:08
 */
@Service("NormalUserCart")
public class NormalUserCart extends AbstractCart {

    @Override
    protected void processCouponPrice(long userId, Item item) {
        item.setCouponPrice(BigDecimal.ZERO);
    }

    @Override
    protected void processDeliveryPrice(long userId, Item item) {
        item.setDeliveryPrice(item.getPrice()
                .multiply(BigDecimal.valueOf(item.getQuantity()))
                .multiply(new BigDecimal("0.1")));
    }
}
