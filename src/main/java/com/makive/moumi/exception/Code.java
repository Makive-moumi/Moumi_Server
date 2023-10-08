package com.makive.moumi.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.Optional;
import java.util.function.Predicate;

@Getter
@RequiredArgsConstructor
public enum Code {
    OK(200, HttpStatus.OK, "Request successful"),
    DATA_NOT_FOUND(204, HttpStatus.NO_CONTENT, "Data not found"),

    BAD_REQUEST(400, HttpStatus.BAD_REQUEST, "Bad request"),
    VALIDATION_ERROR(400, HttpStatus.BAD_REQUEST, "Validation error"),
    NOT_FOUND(404, HttpStatus.NOT_FOUND, "Resource not found"),

    INTERNAL_ERROR(500, HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error"),
    DATA_ACCESS_ERROR(500, HttpStatus.INTERNAL_SERVER_ERROR, "Data access error");

    private final Integer code;
    private final HttpStatus httpStatus;
    private final String message;

    public static Code valueOf(HttpStatus httpStatus) {
        if (httpStatus == null) {
            throw new GeneralException("HttpStatus is null.");
        }

        return Arrays.stream(values())
                .filter(errorCode -> errorCode.getHttpStatus() == httpStatus)
                .findFirst()
                .orElseGet(() -> {
                    if (httpStatus.is4xxClientError()) {
                        return Code.BAD_REQUEST;
                    } else if (httpStatus.is5xxServerError()) {
                        return Code.INTERNAL_ERROR;
                    } else {
                        return Code.OK;
                    }
                });
    }

    public String getMessage(Throwable e) {
        return this.getMessage(this.getMessage() + " - " + e.getMessage());
        // 결과 예시 - "Validation error - Reason why it isn't valid"
    }

    public String getMessage(String message) {
        return Optional.ofNullable(message)
                .filter(Predicate.not(String::isBlank))
                .orElse(this.getMessage());
    }

    @Override
    public String toString() {
        return String.format("%s (%d)", this.name(), this.getCode());
    }
}
