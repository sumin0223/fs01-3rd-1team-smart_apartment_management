package com.jjld.domain.noise.repository;

import com.jjld.domain.noise.entity.Enum.ProcessStatus;
import com.jjld.domain.noise.entity.NoiseEvent;
import com.jjld.domain.noise.entity.NoiseEventProcess;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface NoiseEventProcessRepository extends JpaRepository<NoiseEventProcess, Long> {
    // 이벤트별 처리 정보 조회
    Optional<NoiseEventProcess> findByNoiseEvent(NoiseEvent noiseEvent);

    // 승인 대기 이벤트 수 (전체)
    long countByStatus(ProcessStatus status);

    // 오늘 승인 대기 이벤트 수
    long countByStatusAndCreatedAtBetween(ProcessStatus status, LocalDateTime start, LocalDateTime end);

    // 전체/상태별 목록
    Page<NoiseEventProcess> findByStatus(ProcessStatus status, Pageable pageable);

    // 즉시 처리 필요 + 승인 대기 목록
    Page<NoiseEventProcess> findByUrgentBreakTrueAndStatus(ProcessStatus status, Pageable pageable);

    Optional<NoiseEventProcess> findByNoiseEvent_NoiseEventId(Long noiseEventId);
}
