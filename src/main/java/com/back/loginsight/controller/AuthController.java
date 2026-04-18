package com.back.loginsight.controller;

import com.back.loginsight.dto.request.UserLoginRequest;
import com.back.loginsight.dto.response.LoginResponse;
import com.back.loginsight.service.AuthService;
import com.back.loginsight.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService){
        this.authService = authService;
    }

    @Operation(summary = "토큰 재발급", description = "토큰 재발급")
    @PostMapping("/reissue")
    public LoginResponse reissue(@RequestBody UserLoginRequest request) {
        return authService.reissue(request);
    }

}