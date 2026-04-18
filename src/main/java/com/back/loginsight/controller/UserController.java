package com.back.loginsight.controller;

import com.back.loginsight.service.UserService;
import com.back.loginsight.common.ApiResponse;
import com.back.loginsight.dto.request.UserJoinRequest;
import com.back.loginsight.dto.request.UserLoginRequest;
import com.back.loginsight.dto.response.LoginResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @Operation(summary = "회원가입", description = "회원가입을 합니다.")
    @PostMapping("/join")
    public ApiResponse<String> signUp(@RequestBody UserJoinRequest userJoinRequest) {
        return userService.signUp(userJoinRequest);
    }

    @Operation(summary = "로그인", description = "로그인을 합니다.")
    @PostMapping("/login")
    public ApiResponse<LoginResponse> login(@RequestBody UserLoginRequest userLoginRequest) {
        return userService.login(userLoginRequest);
    }


    @Operation(summary = "로그아웃", description = "로그아웃 합니다.")
    @SecurityRequirement(name = "bearerAuth")
    @PostMapping("/logout")
    public ApiResponse<String> logout(Authentication authentication) {
        return userService.logout(authentication.getName());
    }
}
