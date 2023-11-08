package com.example.demo.mistakes.demo21.observer.right;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

/**
 * <p>
 * RightOrderService
 * </p>
 *
 * @author zhenghao
 * @date 2020/9/14 16:15
 */
@Service
public class RightOrderService {
    @Autowired
    private ApplicationContext applicationContext;

    public void saveOrder(String name) {
        //1创建订单
        System.out.println("创建订单代码！");
        //通过Spring发布一个订单创建事件，监听者收到后执行相应逻辑
        applicationContext.publishEvent(new OrderEvent(name));
    }
}
