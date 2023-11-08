package com.example.demo.mistakes.demo21.observer.ordinary;

import org.springframework.stereotype.Service;

/**
 * <p>
 * OrderService
 * </p>
 *
 * @author zhenghao
 * @date 2020/9/14 16:12
 */
@Service
public class OrdinaryOrderService {

    public void saveOrder(String name){
        //1 创建订单
        System.out.println("创建订单业务逻辑代码！");
        //2 调用接口通知仓库包装
        System.out.println("调用接口通知仓库包装业务逻辑代码！");
        //3 调用物流通知发货
        System.out.println("调用物流通知发货业务逻辑代码！");

        /*
        对照设计原则，一个代码里有多种业务逻辑不符合单一职责，如果现在想加例外一个业务:给用户发短信，那么需要修改saveOrder类，不符合开闭原则。
         */
    }
}
