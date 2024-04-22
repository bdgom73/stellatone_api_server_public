package com.stellive.music.domain.api.global.exception;

public class BizException extends RuntimeException {

    private ErrorResult result;

    public BizException() {
    }
    public BizException(ErrorResult result) {
        super(result.getMessage());
        this.result = result;
    }

    public ErrorResult getResult() {
        return result;
    }
}
