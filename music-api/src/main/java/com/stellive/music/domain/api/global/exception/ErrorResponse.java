package com.stellive.music.domain.api.global.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ErrorResponse {

    private int code;
    private int status;
    private String message;

    protected ErrorResponse(int code, int status, String message) {
        this.code = code;
        this.status = status;
        this.message = message;
    }

    public static ErrorResponse error(HttpStatus status, String message) {
        return new ErrorResponse(-1, status.value(), message);
    }

    public static ErrorResponse error(int status, String message) {
        return new ErrorResponse(-1, status, message);
    }

    public static ErrorResponse error(ErrorResult result) {
        return new ErrorResponse(result.getStatus(), 400, result.getMessage());
    }
}
