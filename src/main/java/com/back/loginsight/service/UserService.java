package com.back.loginsight.service;

import com.back.loginsight.common.ApiResponse;
import com.back.loginsight.dto.request.UserJoinRequest;
import com.back.loginsight.dto.request.UserLoginRequest;
import com.back.loginsight.dto.response.LoginResponse;

public interface UserService {

    ApiResponse<String> signUp(UserJoinRequest userJoinRequest);

    ApiResponse<LoginResponse> login(UserLoginRequest request);

    ApiResponse<String> logout(String loginId);
}
