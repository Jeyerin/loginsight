package com.back.loginsight.dto.response;

import com.back.loginsight.entity.LogAnalysis;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class LogAnalysisHistoryResponse {

    private final String analysisId;
    private final String originalLog;
    private final String summary;
    private final String cause;
    private final List<String> solutions;
    private final String severity;
    private final LocalDateTime createdAt;

    public LogAnalysisHistoryResponse(String analysisId, String originalLog, String summary,
                                      String cause, List<String> solutions, String severity,
                                      LocalDateTime createdAt) {
        this.analysisId = analysisId;
        this.originalLog = originalLog;
        this.summary = summary;
        this.cause = cause;
        this.solutions = solutions;
        this.severity = severity;
        this.createdAt = createdAt;
    }

    public static LogAnalysisHistoryResponse from(LogAnalysis logAnalysis) {
        List<String> parsedSolutions = logAnalysis.getSolutions() == null || logAnalysis.getSolutions().isBlank()
                ? List.of()
                : Arrays.asList(logAnalysis.getSolutions().split("\\n"));

        return new LogAnalysisHistoryResponse(
                logAnalysis.getAnalysisId(),
                logAnalysis.getOriginalLog(),
                logAnalysis.getSummary(),
                logAnalysis.getCause(),
                parsedSolutions,
                logAnalysis.getSeverity(),
                logAnalysis.getCreatedAt()
        );
    }

    public String getAnalysisId() {
        return analysisId;
    }

    public String getOriginalLog() {
        return originalLog;
    }

    public String getSummary() {
        return summary;
    }

    public String getCause() {
        return cause;
    }

    public List<String> getSolutions() {
        return solutions;
    }

    public String getSeverity() {
        return severity;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
