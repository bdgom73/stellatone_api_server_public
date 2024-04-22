package com.stellive.music.domain.api.global.api;

import lombok.Getter;

@Getter
public class DataResponse<T> {
    private T data;

    public DataResponse(T data) {
        this.data = data;
    }

    public static <T> DataResponse<T> send(T data) {
        return new DataResponse<>(data);
    }
}
