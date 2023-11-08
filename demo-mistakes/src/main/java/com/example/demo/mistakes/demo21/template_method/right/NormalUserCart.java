package com.example.demo.mistakes.demo21.template_method.right;

import com.example.demo.mistakes.demo21.template_method.Item;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * <p>
 * TODO
 * </p>
 *
 * @author zhenghao
 * @date 2020/9/10 16:05
 */
@Service(value = "NormalUserCart")
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
