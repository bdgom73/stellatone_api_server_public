package com.stellive.music.domain.api.global.api;

import lombok.Getter;

@Getter
public class ApiResponse {
    private int code;
    private String message;

    private ApiResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public static ApiResponse ok() {
        return new ApiResponse(0, "SUCCESS");
    }
}
