package com.back.loginsight.service;

import com.back.loginsight.common.ApiResponse;
import com.back.loginsight.dto.request.LogAnalysisRequest;
import com.back.loginsight.dto.response.LogAnalysisResponse;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class LogAnalysisImpl implements LogAnalysisService {

    private final RestClient restClient;
    private final String pythonAnalyzePath;

    public LogAnalysisImpl(@Value("${log-analysis.python.host:localhost}") String pythonHost,
                           @Value("${log-analysis.python.port:8000}") int pythonPort,
                           @Value("${log-analysis.python.path:/analyze}") String pythonAnalyzePath) {
        this.pythonAnalyzePath = pythonAnalyzePath;
        this.restClient = RestClient.builder()
                .baseUrl("http://" + pythonHost + ":" + pythonPort)
                .build();
    }

    @Override
    public ApiResponse<LogAnalysisResponse> analyze(LogAnalysisRequest request) {
        String logText = request.getLogText();

        if (logText == null || logText.isBlank()) {
            throw new IllegalArgumentException("로그 텍스트는 비어 있을 수 없습니다.");
        }

        LogAnalysisResponse logAnalysisResponse = runPythonAnalysis(logText);
        return ApiResponse.success("로그 분석 완료", logAnalysisResponse);
    }

    private LogAnalysisResponse runPythonAnalysis(String logText) {
        try {
            PythonAnalysisResult response = restClient.post()
                    .uri(pythonAnalyzePath)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(new PythonAnalysisRequest(logText))
                    .retrieve()
                    .body(PythonAnalysisResult.class);

            if (response == null) {
                throw new IllegalStateException("Python 분석 응답이 비어 있습니다.");
            }

            return new LogAnalysisResponse(
                    response.summary() != null ? response.summary() : "요약 없음",
                    response.cause() != null ? response.cause() : "원인 분석 실패",
                    response.solutions() != null ? response.solutions() : List.of(),
                    response.severity() != null ? response.severity() : "UNKNOWN"
            );
        } catch (Exception e) {
            throw new IllegalStateException("Python 분석 서버 호출에 실패했습니다.", e);
        }
    }

    private record PythonAnalysisRequest(String logText) {
    }

    private record PythonAnalysisResult(
            String summary,
            String cause,
            List<String> solutions,
            String severity
    ) {
    }
}
