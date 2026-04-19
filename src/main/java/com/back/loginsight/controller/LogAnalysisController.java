package com.back.loginsight.controller;

import com.back.loginsight.common.ApiResponse;
import com.back.loginsight.dto.request.LogAnalysisRequest;
import com.back.loginsight.dto.response.LogAnalysisResponse;
import com.back.loginsight.service.LogAnalysisService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/logs")
public class LogAnalysisController {
    private final LogAnalysisService logAnalysisService;

    public LogAnalysisController(LogAnalysisService logAnalysisService) {
        this.logAnalysisService = logAnalysisService;
    }

    @Operation(summary = "로그 분석 요청", description = "로그 분석을 합니다.")
    @SecurityRequirement(name = "bearerAuth")
    @PostMapping("/analyze")
    public ApiResponse<LogAnalysisResponse> analyze(@RequestBody LogAnalysisRequest request) {
        return logAnalysisService.analyze(request);
    }

}
