package com.example.demo.mistakes.demo21.reflection.right;

import java.lang.annotation.*;

/**
 * <p>
 * 自定义注解 @BankAPIField，用于描述接口的每一个字段规范，包含参数的次序、类型和长度三个属性：
 * </p>
 *
 * @author zhenghao
 * @date 2020/9/10 16:23
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Documented
@Inherited
public @interface BankAPIField {
    int order() default -1;
    int length() default -1;
    String type() default "";
}
