package com.nacrt.demo.jdknew.jdk8.f_base64;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * 工具类
 *
 * @author zhenghao
 * @date 2022/3/30 21:26
 */
public class Base64Util {
    public static String encode(String str) {
        return new String(Base64.getEncoder().encode(str.getBytes(StandardCharsets.UTF_8)));
    }
    public static String decode(String str) {
        return new String(Base64.getDecoder().decode(str), StandardCharsets.UTF_8);
    }
    public static String urlEncode(String str) {
        return new String(Base64.getUrlEncoder().encode(str.getBytes(StandardCharsets.UTF_8)));
    }
    public static String urlDecode(String str) {
        return new String(Base64.getUrlDecoder().decode(str), StandardCharsets.UTF_8);
    }
    public static String mimeEncode(String str) {
        return new String(Base64.getMimeEncoder().encode(str.getBytes(StandardCharsets.UTF_8)));
    }
    public static String mimeDecode(String str) {
        return new String(Base64.getMimeDecoder().decode(str), StandardCharsets.UTF_8);
    }
}
