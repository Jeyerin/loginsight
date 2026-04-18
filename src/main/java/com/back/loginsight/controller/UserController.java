package com.back.loginsight.controller;

import com.back.loginsight.config.CustomUserPrincipal;
import com.back.loginsight.dto.request.UserInfoRequest;
import com.back.loginsight.dto.response.UserInfoResponse;
import com.back.loginsight.service.UserService;
import com.back.loginsight.common.ApiResponse;
import com.back.loginsight.dto.request.UserJoinRequest;
import com.back.loginsight.dto.request.UserLoginRequest;
import com.back.loginsight.dto.response.LoginResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

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

    @Operation(summary = "사용자 정보 수정", description = "인증된 사용자의 정보를 수정합니다.")
    @SecurityRequirement(name = "bearerAuth")
    @PostMapping("/update")
    public ApiResponse<String> update(@AuthenticationPrincipal CustomUserPrincipal principal,
                                      @RequestBody UserInfoRequest request) {
        return userService.updateUser(principal.getLoginId(), request);
    }

    @Operation(summary = "내 정보 조회", description = "인증된 사용자의 정보를 조회합니다.")
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/info")
    public ApiResponse<UserInfoResponse> getUserInfo(@AuthenticationPrincipal CustomUserPrincipal principal) {
        return userService.getUserInfo(principal.getLoginId());
    }
}
