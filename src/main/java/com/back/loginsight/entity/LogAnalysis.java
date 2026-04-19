package com.back.loginsight.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "log_analysis", schema = "public")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LogAnalysis {

    @Id
    @Column(name = "analysis_id", nullable = false, updatable = false)
    private String analysisId;

    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(name = "original_log", nullable = false, columnDefinition = "TEXT")
    private String originalLog;

    @Column(name = "summary", columnDefinition = "TEXT")
    private String summary;

    @Column(name = "cause", columnDefinition = "TEXT")
    private String cause;

    @Column(name = "solutions", columnDefinition = "TEXT")
    private String solutions;

    @Column(name = "severity", length = 20)
    private String severity;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Builder
    public LogAnalysis(String analysisId, String userId, String originalLog,
                       String summary, String cause, String solutions,
                       String severity, LocalDateTime createdAt) {
        this.analysisId = analysisId;
        this.userId = userId;
        this.originalLog = originalLog;
        this.summary = summary;
        this.cause = cause;
        this.solutions = solutions;
        this.severity = severity;
        this.createdAt = createdAt;
    }
}
