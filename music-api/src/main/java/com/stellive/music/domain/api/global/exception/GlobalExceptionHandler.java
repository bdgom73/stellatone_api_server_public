package com.stellive.music.domain.api.global.exception;

import jakarta.validation.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({ ValidationException.class })
    protected ResponseEntity<ErrorResponse> handleValidationExceptionException(ValidationException e) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status)
                .body(ErrorResponse.error(status, e.getMessage()));
    }

    @ExceptionHandler({ BizException.class })
    protected ResponseEntity<ErrorResponse> handleBizExceptionException(BizException e) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status)
                .body(ErrorResponse.error(e.getResult()));
    }

    @ExceptionHandler({ AuthenticationException.class })
    protected ResponseEntity<ErrorResponse> handleAuthenticationExceptionException(AuthenticationException e) {
        HttpStatus status = HttpStatus.UNAUTHORIZED;
        return ResponseEntity
                .status(status)
                .body(ErrorResponse.error(status, e.getMessage()));
    }

    @ExceptionHandler({ IllegalArgumentException.class })
    protected ResponseEntity<ErrorResponse> handleIllegalArgumentExceptionException(IllegalArgumentException e) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status)
                .body(ErrorResponse.error(status, e.getMessage()));
    }

    @ExceptionHandler({ Exception.class })
    protected ResponseEntity<ErrorResponse> handleException(Exception e) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        return ResponseEntity.status(status)
                .body(ErrorResponse.error(status, e.getMessage()));
    }
}
