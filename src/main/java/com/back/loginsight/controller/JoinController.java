package com.back.loginsight.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/join")
public class JoinController {

    @Operation(summary = "회원가입", description = "회원가입을 합니다.")
    @GetMapping("/reg")
    public String signUp(
            @Parameter(description = "이름",example = "홍길동",required = true) String name) {
        return "hello";
    }
}