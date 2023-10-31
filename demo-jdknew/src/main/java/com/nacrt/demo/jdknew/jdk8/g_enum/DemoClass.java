package com.nacrt.demo.jdknew.jdk8.g_enum;

import lombok.Data;

/**
 * @author mars
 * @description DemoClass
 * @date 2023/10/31 14:21
 */
@Data
public class DemoClass implements IBaseEnum<String> {
    public DemoClass(String code, String msg) {
        init(code, msg);
    }
}
