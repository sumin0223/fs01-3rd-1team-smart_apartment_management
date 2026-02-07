package com.jjld.domain.noise.repository;

import com.jjld.domain.noise.entity.NoiseEvent;
import com.jjld.domain.noise.entity.NoiseEventAnalysis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface NoiseEventAnalysisRepository extends JpaRepository<NoiseEventAnalysis, Long> {
    // 이벤트에 대한 분석 단건 조회
    Optional<NoiseEventAnalysis> findByNoiseEvent(NoiseEvent noiseEvent);

    // 오늘 정책 위반 의심 이벤트 수
    long countByPolicyBreakTrueAndCreatedAtBetween(LocalDateTime start, LocalDateTime end);
    // 특정기간 내 분석결과 조회(통계용)
    List<NoiseEventAnalysis> findByCreatedAtBetween(LocalDateTime start, LocalDateTime end);
}
