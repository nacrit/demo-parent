package com.example.demo.mistakes.demo22.api_resposne;

import lombok.Getter;

/**
 * <p>
 * APIException
 * </p>
 *
 * @author zhenghao
 * @date 2020/9/15 15:04
 */
public class APIException extends RuntimeException {
    @Getter
    private int errorCode;
    @Getter
    private String errorMessage;

    public APIException(int errorCode, String errorMessage) {
        super(errorMessage);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public APIException(Throwable cause, int errorCode, String errorMessage) {
        super(errorMessage, cause);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
}
