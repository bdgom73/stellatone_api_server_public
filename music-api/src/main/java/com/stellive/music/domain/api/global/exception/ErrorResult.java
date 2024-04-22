package com.stellive.music.domain.api.global.exception;

public enum ErrorResult {

    /** 기본 에러 */
    BAD_REQUEST(400, "Bad Request"),
    UNAUTHORIZED(401, "Unauthorized"),
    FORBIDDEN(403, "Forbidden"),
    NOT_FOUND(404, "Not Found"),
    INTERNAL_SERVER_ERROR(500, "Internal Server Error"),

    /** 커스텀 에러 */
    INVALID_TOKEN(1000, "올바르지 않은 토큰"),

    NOT_FOUND_MEMBER(2000, "찾을 수 없는 회원"),

    NOT_FOUND_PLAYLIST(2100, "찾을 수 없는 플레이리스트"),
    NOT_FOUND_PLAYLIST_ITEM(2101, "찾을 수 없는 플레이리스트 아이템"),

    NOT_FOUND_MUSIC(2200, "찾을 수 없는 음악"),;


    private final int status;
    private final String message;

    ErrorResult(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
