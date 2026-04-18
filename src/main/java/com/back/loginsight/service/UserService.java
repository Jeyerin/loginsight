package com.back.loginsight.service;

import com.back.loginsight.common.ApiResponse;
import com.back.loginsight.dto.request.UserInfoRequest;
import com.back.loginsight.dto.request.UserJoinRequest;
import com.back.loginsight.dto.request.UserLoginRequest;
import com.back.loginsight.dto.response.LoginResponse;
import com.back.loginsight.dto.response.UserInfoResponse;

public interface UserService {

    ApiResponse<String> signUp(UserJoinRequest userJoinRequest);

    ApiResponse<LoginResponse> login(UserLoginRequest request);

    ApiResponse<String> logout(String loginId);

    ApiResponse<String> updateUser(String loginId, UserInfoRequest request);

    ApiResponse<UserInfoResponse> getUserInfo(String loginId);
}
