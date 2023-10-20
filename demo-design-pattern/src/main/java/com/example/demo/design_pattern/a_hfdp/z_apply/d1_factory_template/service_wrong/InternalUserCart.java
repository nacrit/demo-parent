package com.example.demo.design_pattern.a_hfdp.z_apply.d1_factory_template.service_wrong;

import com.example.demo.design_pattern.a_hfdp.z_apply.d1_factory_template.dao.Db;
import com.example.demo.design_pattern.a_hfdp.z_apply.d1_factory_template.model.Cart;
import com.example.demo.design_pattern.a_hfdp.z_apply.d1_factory_template.model.Item;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 免运费、无折扣的内部用户
 *
 * @author zhenghao
 * @date 2022/4/21 15:02
 */
@Service
public class InternalUserCart {

    public Cart process(long userId, Map<Long, Integer> items) {
        // 购物车
        Cart cart = new Cart();
        //把Map的购物车转换为Item列表
        List<Item> itemList = new ArrayList<>();
        items.forEach((key, value) -> {
            Item item = new Item();
            item.setId(key);
            item.setPrice(Db.getItemPrice(key));
            item.setQuantity(value);
            itemList.add(item);
        });
        cart.setItems(itemList);

        //处理运费和商品优惠
        itemList.forEach(item -> {
            //免运费
            item.setDeliveryPrice(BigDecimal.ZERO);
            //无优惠
            item.setCouponPrice(BigDecimal.ZERO);
        });

        //计算商品总价：单价*数量求和
        cart.setTotalItemPrice(cart.getItems().stream().map(item -> item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity()))).reduce(BigDecimal.ZERO, BigDecimal::add));
        //计算运费总价：运费求和
        cart.setTotalDeliveryPrice(cart.getItems().stream().map(Item::getDeliveryPrice).reduce(BigDecimal.ZERO, BigDecimal::add));
        //计算总优惠：优惠求和
        cart.setTotalDiscount(cart.getItems().stream().map(Item::getCouponPrice).reduce(BigDecimal.ZERO, BigDecimal::add));
        //应付总价=商品总价+运费总价-总优惠
        cart.setPayPrice(cart.getTotalItemPrice().add(cart.getTotalDeliveryPrice()).subtract(cart.getTotalDiscount()));
        return cart;
    }

}
