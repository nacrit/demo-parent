package com.nacrt.demo.jdknew.jdk8.g_enum;

/**
 * @author mars
 * @description DemoEnum
 * @date 2023/10/30 14:20
 */
public enum DemoEnum implements IBaseEnum<Integer> {
    SUCCESS(200, "成功"),
    FAIL(500, "失败");

    DemoEnum(Integer code, String msg) {
        init(code, msg);
    }

}
