package com.example.demo.mistakes.demo21.beancopy;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.digest.MD5;

/**
 * <p>
 * Hutool测试
 * </p>
 *
 * @author zhenghao
 * @date 2020/9/11 10:07
 */
public class HuToolTest {
    public static void main(String[] args) {
        MD5 md5 = SecureUtil.md5();
        BeanUtil.copyProperties(null, "", "id");

    }
}
