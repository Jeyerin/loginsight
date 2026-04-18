package com.back.loginsight.common;

public enum ErrorCode {

    // USER
    USER_DUPLICATE("USER_001", "이미 존재하는 사용자입니다", 400),
    USER_NOT_FOUND("USER_002", "사용자를 찾을 수 없습니다", 404),

    // AUTH
    LOGIN_FAIL("AUTH_001", "아이디 또는 비밀번호가 틀렸습니다", 401),
    TOKEN_EXPIRED("AUTH_002", "토큰이 만료되었습니다", 401),

    // COMMON
    INTERNAL_ERROR("COMMON_999", "서버 오류가 발생했습니다", 500);

    private final String code;
    private final String message;
    private final int status;

    ErrorCode(String code, String message, int status) {
        this.code = code;
        this.message = message;
        this.status = status;
    }

    public String getCode() { return code; }
    public String getMessage() { return message; }
    public int getStatus() { return status; }
}