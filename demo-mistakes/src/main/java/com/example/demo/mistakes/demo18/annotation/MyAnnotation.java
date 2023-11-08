package com.example.demo.mistakes.demo18.annotation;


import java.lang.annotation.*;

//@Target({ElementType.METHOD, ElementType.TYPE})
//@Retention(RetentionPolicy.RUNTIME)
//public @interface MyAnnotation {
//    String value();
//}


@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface MyAnnotation {
    String value();
}