package com.back.loginsight.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserInfoRequest {

    private String password;
    private String email;
    private String phoneNumber;
}
