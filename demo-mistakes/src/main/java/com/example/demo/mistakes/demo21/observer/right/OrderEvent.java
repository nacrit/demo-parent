package com.example.demo.mistakes.demo21.observer.right;

import org.springframework.context.ApplicationEvent;

/**
 * <p>
 * OrderEvent
 * </p>
 *
 * @author zhenghao
 * @date 2020/9/14 16:17
 */
public class OrderEvent extends ApplicationEvent {

    public OrderEvent(Object source) {
        super(source);
    }
}
