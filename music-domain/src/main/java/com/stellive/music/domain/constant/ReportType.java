package com.stellive.music.domain.constant;

public enum ReportType {

    VE0001("This video can no longer be played","이 동영상을 더 이상 재생할 수 없습니다."),
    VE0002("The artist and the music don't match","아티스트와 음악이 맞지 않습니다.");

    private final String code;
    private final String message;

    ReportType(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
