package com.back.loginsight.service;

import com.back.loginsight.common.ApiResponse;
import com.back.loginsight.dto.request.LogAnalysisRequest;
import com.back.loginsight.dto.response.LogAnalysisHistoryResponse;
import com.back.loginsight.dto.response.LogAnalysisResponse;
import java.util.List;

public interface LogAnalysisService {
    ApiResponse<LogAnalysisResponse> analyze(String loginId, LogAnalysisRequest logAnalysisRequest);

    ApiResponse<List<LogAnalysisHistoryResponse>> getHistory(String loginId);
}
