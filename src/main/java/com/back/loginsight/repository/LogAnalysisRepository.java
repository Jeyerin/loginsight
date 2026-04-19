package com.back.loginsight.repository;

import com.back.loginsight.entity.LogAnalysis;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogAnalysisRepository extends JpaRepository<LogAnalysis, String> {

    List<LogAnalysis> findByUserIdOrderByCreatedAtDesc(String userId);
}
