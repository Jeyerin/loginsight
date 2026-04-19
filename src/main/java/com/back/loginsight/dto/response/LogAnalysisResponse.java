package com.back.loginsight.dto.response;

import java.util.List;

public class LogAnalysisResponse {

    private String summary;
    private String cause;
    private List<String> solutions;
    private String severity;

    public LogAnalysisResponse(String summary, String cause, List<String> solutions, String severity) {
        this.summary = summary;
        this.cause = cause;
        this.solutions = solutions;
        this.severity = severity;
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
}