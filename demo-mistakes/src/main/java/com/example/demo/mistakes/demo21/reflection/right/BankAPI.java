package com.example.demo.mistakes.demo21.reflection.right;

import java.lang.annotation.*;

/**
 * 接口 API 的注解 BankAPI，包含接口 URL 地址和接口说明
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Inherited
public @interface BankAPI {
    String desc() default "";
    String url() default "";
}