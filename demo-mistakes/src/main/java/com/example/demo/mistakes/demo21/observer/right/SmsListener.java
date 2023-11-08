package com.example.demo.mistakes.demo21.observer.right;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.SmartApplicationListener;

/**
 * <p>
 * SmsListener
 * </p>
 *
 * @author zhenghao
 * @date 2020/9/14 16:16
 */
public class SmsListener implements SmartApplicationListener {
    // 收到事件后，执行的业务
    public void onApplicationEvent(ApplicationEvent event) {
        System.out.println("执行发短信的业务");
    }

    public int getOrder() {
        // 多个监听者，可以定义顺序
        return 60;
    }

    public boolean supportsEventType(Class<? extends ApplicationEvent> eventType) {
        return eventType == OrderEvent.class;
    }

    public boolean supportSourceType(Class<?> sourceType) {
        return true;
    }
}
