package com.example.demo.springboot.regist_bean;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class SpringBeanUtils implements ApplicationContextAware {
    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringBeanUtils.applicationContext = applicationContext;
    }

    //获取applicationContext
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    //通过name获取 Bean.
    public static Object getBean(String name) {
        return applicationContext.getBean(name);
    }

    //通过class获取Bean.
    public static <T> T getBean(Class<T> clazz) {
        return applicationContext.getBean(clazz);
    }

    //通过name,以及Clazz返回指定的Bean
    public static <T> T getBean(String name, Class<T> clazz) {
        return applicationContext.getBean(name, clazz);
    }

    // 将bean注册到容器中
    public static boolean putBean(String beanName, Object bean) {
        // 已经存在同名单例bean
        if (applicationContext.containsBean(beanName)) return false;
        ((GenericApplicationContext) applicationContext).getBeanFactory().registerSingleton(beanName, bean);
        return true;
    }

    //判断Beam是否存在
    public static boolean containsBean(String name){
        return applicationContext.containsBean(name);
    }
}
