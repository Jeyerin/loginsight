package com.back.loginsight.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ApiResponse<T> {

    private boolean success; // 성공 여부
    private String message; // 실패 메시지
    private T data; // 반환 데이터

    public static <T> ApiResponse<T> success() {
        return new ApiResponse<>(true, "success", null);
    }

    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(true, "success", data);
    }

    public static <T> ApiResponse<T> success(String message, T data) {
        return new ApiResponse<>(true, message, data);
    }

    public static <T> ApiResponse<T> fail(String message) {
        return new ApiResponse<>(false, message, null);
    }
}