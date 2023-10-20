package com.example.demo.design_pattern.a_head_first_design_patterns.z_apply.d2_annotation_reflaction.service_right;

import java.lang.annotation.*;

/**
 * api
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Inherited
public @interface BankAPI {
    String desc() default "";
    String url() default "";
}
