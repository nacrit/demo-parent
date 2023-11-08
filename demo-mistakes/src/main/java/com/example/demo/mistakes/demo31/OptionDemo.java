package com.example.demo.mistakes.demo31;

import java.util.Optional;
import java.util.OptionalDouble;

/**
 * @author zhenghao
 * @description Option Demo
 * @date 2020/6/17 11:06
 */
public class OptionDemo {

    /**
     *
     */
    public static void main(String[] args) {
        optional();
    }


    public static void optional() {
        //通过get方法获取Optional中的实际值
        System.out.println(Optional.of(1).get());

        //通过ofNullable来初始化一个null，通过orElse方法实现Optional中无数据的时候返回一个默认值
        System.out.println(Optional.ofNullable(null).orElse("A"));

        //OptionalDouble是基本类型double的Optional对象，isPresent判断有无数据
        System.out.println(OptionalDouble.empty().isPresent());

        //通过map方法可以对Optional对象进行级联转换，不会出现空指针，转换后还是一个Optional
        System.out.println(Optional.of(1).map(Math::incrementExact).get());

        //通过filter实现Optional中数据的过滤，得到一个Optional，然后级联使用orElse提供默认值
        System.out.println(Optional.of(1).filter(integer -> integer % 2 == 0).orElse(null));

        //通过orElseThrow实现无数据时抛出异常
        try {
            Optional.empty().orElseThrow(IllegalArgumentException::new);
        } catch (IllegalArgumentException e) {
            System.out.println("IllegalArgumentException");
        }
    }
}
