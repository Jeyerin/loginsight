package com.back.loginsight.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "log_analysis")
public class LogAnalysis {

    @Id
    @Column(name = "analysis_id", nullable = false, updatable = false)
    private UUID analysisId;

    @Column(name = "user_id", nullable = false)
    private UUID userId;

    @Column(name = "original_log", nullable = false, columnDefinition = "TEXT")
    private String originalLog;

    @Column(name = "summary", columnDefinition = "TEXT")
    private String summary;

    @Column(name = "cause", columnDefinition = "TEXT")
    private String cause;

    @Column(name = "severity", length = 20)
    private String severity;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    protected LogAnalysis() {}

    public LogAnalysis(UUID analysisId, UUID userId, String originalLog,
                       String summary, String cause, String severity, LocalDateTime createdAt) {
        this.analysisId = analysisId;
        this.userId = userId;
        this.originalLog = originalLog;
        this.summary = summary;
        this.cause = cause;
        this.severity = severity;
        this.createdAt = createdAt;
    }
}