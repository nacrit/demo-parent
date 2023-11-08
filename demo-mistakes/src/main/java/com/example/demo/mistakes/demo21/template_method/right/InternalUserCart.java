package com.example.demo.mistakes.demo21.template_method.right;

import com.java.error.example.demo.error.demo21.template_method.Item;
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
@Service(value = "InternalUserCart")
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