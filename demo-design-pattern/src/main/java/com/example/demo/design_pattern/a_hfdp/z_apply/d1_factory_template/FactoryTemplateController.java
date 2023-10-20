package com.example.demo.design_pattern.a_hfdp.z_apply.d1_factory_template;

import com.example.demo.design_pattern.a_hfdp.z_apply.d1_factory_template.dao.Db;
import com.example.demo.design_pattern.a_hfdp.z_apply.d1_factory_template.model.Cart;
import com.example.demo.design_pattern.a_hfdp.z_apply.d1_factory_template.service_right.AbstractCart;
import com.example.demo.design_pattern.a_hfdp.z_apply.d1_factory_template.service_wrong.InternalUserCart;
import com.example.demo.design_pattern.a_hfdp.z_apply.d1_factory_template.service_wrong.NormalUserCart;
import com.example.demo.design_pattern.a_hfdp.z_apply.d1_factory_template.service_wrong.VipUserCart;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * TestController
 *
 * @author zhenghao
 * @date 2022/4/21 15:25
 */
@Slf4j
@RestController
@RequestMapping("/templatemethod")
public class FactoryTemplateController {
    @Autowired
    private NormalUserCart normalUserCart;
    @Autowired
    private VipUserCart vipUserCart;
    @Autowired
    private InternalUserCart internalUserCart;


    private static Map<Long, Integer> items = new HashMap<>();

    static {
        items.put(1L, 2);
        items.put(2L, 4);
    }

    private final ApplicationContext applicationContext;

    public FactoryTemplateController(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    // 普通实现
    @GetMapping("wrong")
    public Cart wrong(@RequestParam("userId") int userId) {
        // 用户1=普通用户，用户2=vip，用户3=内部，其它都是普通
        String userCategory = Db.getUserCategory(userId);
        if (userCategory.equals("Normal")) {
            return normalUserCart.process(userId, items);
        }
        if (userCategory.equals("Vip")) {
            return vipUserCart.process(userId, items);
        }
        if (userCategory.equals("Internal")) {
            return internalUserCart.process(userId, items);
        }
        return null;
    }

    // 模板方法实现
    @GetMapping("right")
    public Cart right(@RequestParam("userId") int userId) {
        String userCategory = Db.getUserCategory(userId);
        AbstractCart cart = applicationContext.getBean(userCategory + "UserCart", AbstractCart.class);
        return cart.process(userId, items);
    }
}
