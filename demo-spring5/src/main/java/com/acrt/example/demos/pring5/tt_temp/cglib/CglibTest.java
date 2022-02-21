package com.acrt.example.demos.pring5.tt_temp.cglib;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * <p>
 * CglibTest
 * </p>
 *
 * @author zhenghao
 * @date 2021/1/14 17:30
 */
public class CglibTest {
    public static void main(String[] args) {
        User target = new User();
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(target.getClass());
        // 设置回调方法
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object object, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                System.out.println(method.getName() + "方法开始执行。。。");
                Object result = methodProxy.invokeSuper(object, args);
                System.out.println(method.getName() + "方法结束执行。。。");
                return result;
            }
        });
        // 创建代理对象
        User user = (User)enhancer.create();
        user.say();
    }
}
