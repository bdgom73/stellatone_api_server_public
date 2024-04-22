package com.stellive.music.domain.constant;

public enum ReportStatus {
    PROGRESS("진행중"),
    COMPLETE("완료");

    private final String text;

    ReportStatus(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
