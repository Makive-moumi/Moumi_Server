package com.makive.moumi.exception.dto;

import com.makive.moumi.exception.Code;

public class ErrorResponse extends Response {
    private ErrorResponse(Code errorCode) {
        super(false, errorCode.getCode(), errorCode.getMessage());
    }

    private ErrorResponse(Code errorCode, Exception e) {
        super(false, errorCode.getCode(), errorCode.getMessage(e));
    }

    private ErrorResponse(Code errorCode, String message) {
        super(false, errorCode.getCode(), errorCode.getMessage(message));
    }

    public static ErrorResponse of(Code errorCode) {
        return new ErrorResponse(errorCode);
    }

    public static ErrorResponse of(Code errorCode, Exception e) {
        return new ErrorResponse(errorCode, e);
    }

    public static ErrorResponse of(Code errorCode, String message) {
        return new ErrorResponse(errorCode, message);
    }
}