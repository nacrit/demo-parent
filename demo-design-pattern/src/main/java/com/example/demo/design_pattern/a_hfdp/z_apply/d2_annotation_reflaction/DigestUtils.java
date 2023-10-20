package com.example.demo.design_pattern.a_hfdp.z_apply.d2_annotation_reflaction;

import java.nio.charset.StandardCharsets;

/**
 * DigestUtils
 *
 * @author zhenghao
 * @date 2022/4/21 16:00
 */
public class DigestUtils {
    public static String md5(String str) {
        return new String(org.springframework.util.DigestUtils.md5Digest(
                str.getBytes(StandardCharsets.UTF_8)), StandardCharsets.UTF_8);
    }
}
