package com.example.demo.design_pattern.z_apply.d2_annotation_reflaction.service_right;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * 银行参数字段类型
 *
 * @author zhenghao
 * @date 2022/4/21 16:20
 */
public enum BankAPIFieldType {
    S {
        @Override
        public String format(Object value, BankAPIField bankAPIField) {
            return String.format("%-" + bankAPIField.length() + "s", value.toString()).replace(' ', '_');
        }
    }, N {
        @Override
        public String format(Object value, BankAPIField bankAPIField) {
            return String.format("%" + bankAPIField.length() + "s", value.toString()).replace(' ', '0');
        }
    }, M {
        @Override
        public String format(Object value, BankAPIField bankAPIField) {
            return String.format("%0" + bankAPIField.length() + "d",
                    ((BigDecimal) value).setScale(2, RoundingMode.DOWN).multiply(new BigDecimal("100")).longValue());
        }
    },;

    public abstract String format(Object value, BankAPIField bankAPIField);
}
