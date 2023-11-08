package com.example.demo.mistakes.demo21.template_method;

import lombok.Data;

import java.math.BigDecimal;

/**
 * <p>
 * 购物车中的商品
 * </p>
 *
 * @author zhenghao
 * @date 2020/9/10 15:01
 */
@Data
public class Item {
    //商品ID
    private long id;
    //商品数量
    private int quantity;
    //商品单价
    private BigDecimal price;
    //商品优惠
    private BigDecimal couponPrice;
    //商品运费
    private BigDecimal deliveryPrice;
}
