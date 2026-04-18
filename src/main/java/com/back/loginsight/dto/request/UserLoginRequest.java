package com.back.loginsight.dto.request;

import lombok.Getter;

@Getter
public class UserLoginRequest {
    private String loginId;
    private String password;
}
