package com.nacrt.demo.jdknew.jdk8.g_enum;

/**
 * @author mars
 * @description DemoEnum
 * @date 2023/10/30 14:20
 */
public enum Demo2Enum implements IBaseEnum<String> {
    OPEN("open", "开启"),
    CLOSE("close", "关闭"),
    HELLO("close", "你好")
    ;

    Demo2Enum(String code, String msg) {
        init(code, msg);
    }

}
