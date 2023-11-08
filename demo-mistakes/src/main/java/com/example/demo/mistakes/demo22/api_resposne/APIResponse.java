package com.example.demo.mistakes.demo22.api_resposne;

import lombok.Data;

/**
 * <p>
 * APIResponse
 * </p>
 *
 * @author zhenghao
 * @date 2020/9/14 10:31
 */
@Data
public class APIResponse<T> {
    private boolean success;
    private T data;
    private int code;
    private String message;
}