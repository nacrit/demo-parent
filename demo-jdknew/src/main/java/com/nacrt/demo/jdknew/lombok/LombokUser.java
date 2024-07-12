package com.nacrt.demo.jdknew.lombok;

import lombok.*;

/**
 * @author mars
 * @description User
 * @date 2024/7/11 10:31
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@With(AccessLevel.PRIVATE)
public class LombokUser {
    private String name;
    private Integer age;
}
