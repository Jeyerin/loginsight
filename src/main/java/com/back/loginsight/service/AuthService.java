package com.back.loginsight.service;

import com.back.loginsight.common.ApiResponse;
import com.back.loginsight.dto.request.UserJoinRequest;
import com.back.loginsight.dto.request.UserLoginRequest;
import com.back.loginsight.dto.response.LoginResponse;

public interface AuthService {

    LoginResponse reissue(UserLoginRequest userLoginRequest);

}
