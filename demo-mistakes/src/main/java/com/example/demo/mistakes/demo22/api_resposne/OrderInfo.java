package com.example.demo.mistakes.demo22.api_resposne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>
 * OrderInfo
 * </p>
 *
 * @author zhenghao
 * @date 2020/9/14 10:57
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderInfo {
    private String status;
    private long orderId;
}

