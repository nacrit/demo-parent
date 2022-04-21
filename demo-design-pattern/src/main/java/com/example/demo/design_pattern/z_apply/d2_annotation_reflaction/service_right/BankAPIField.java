package com.example.demo.design_pattern.z_apply.d2_annotation_reflaction.service_right;

import java.lang.annotation.*;

/**
 * 字段注解
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Documented
@Inherited
public @interface BankAPIField {
    int order() default -1;
    int length() default -1;
    BankAPIFieldType bankAPIFieldType();
}
