package com.example.demo.mistakes.demo21.template_method.ordinary;

import com.java.error.example.demo.error.demo21.template_method.Cart;
import com.java.error.example.demo.error.demo21.template_method.Db;
import com.java.error.example.demo.error.demo21.template_method.Item;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>>
 * 普通用户购物车处理:
 *
 * 利用工厂模式 + 模板方法模式，消除 if…else 和重复代码假设要开发一个购物车下单的功能，针对不同用户进行不同处理：
 * 普通用户需要收取运费，运费是商品价格的 10%，无商品折扣；VIP 用户同样需要收取商品价格 10% 的快递费，但购买两件以上相同商品时，
 *      第三件开始享受一定折扣；内部用户可以免运费，无商品折扣。我们的目标是实现三种类型的购物车业务逻辑，
 *      把入参 Map 对象（Key 是商品 ID，Value 是商品数量），转换为出参购物车类型 Cart。
 * </p>
 *
 * @author zhenghao
 * @date 2020/9/10 15:01
 */
//普通用户购物车处理
public class NormalUserCart {
    public Cart process(long userId, Map<Long, Integer> items) {
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

        //普通用户：处理运费和商品优惠
        itemList.forEach(item -> {
            //运费为商品总价的10%
            item.setDeliveryPrice(item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity())).multiply(new BigDecimal("0.1")));
            //无优惠
            item.setCouponPrice(BigDecimal.ZERO);
        });

        //计算商品总价
        cart.setTotalItemPrice(cart.getItems().stream().map(item -> item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity()))).reduce(BigDecimal.ZERO, BigDecimal::add));
        //计算运费总价
        cart.setTotalDeliveryPrice(cart.getItems().stream().map(Item::getDeliveryPrice).reduce(BigDecimal.ZERO, BigDecimal::add));
        //计算总优惠
        cart.setTotalDiscount(cart.getItems().stream().map(Item::getCouponPrice).reduce(BigDecimal.ZERO, BigDecimal::add));
        //应付总价=商品总价+运费总价-总优惠
        cart.setPayPrice(cart.getTotalItemPrice().add(cart.getTotalDeliveryPrice()).subtract(cart.getTotalDiscount()));
        return cart;
    }
}
