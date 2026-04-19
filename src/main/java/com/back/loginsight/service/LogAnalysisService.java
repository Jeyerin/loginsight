package com.back.loginsight.service;

import com.back.loginsight.common.ApiResponse;
import com.back.loginsight.dto.request.LogAnalysisRequest;
import com.back.loginsight.dto.response.LogAnalysisResponse;

public interface LogAnalysisService {
    ApiResponse<LogAnalysisResponse> analyze(LogAnalysisRequest logAnalysisRequest);
}
