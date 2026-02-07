package com.jjld.domain.noise.repository;

import com.jjld.domain.noise.entity.NoiseEvent;
import com.jjld.domain.noise.entity.NoiseSensor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface NoiseEventRepository extends JpaRepository<NoiseEvent, Long> {
    // 오늘 발생 이벤트 수
    long countByCreatedAtBetween(LocalDateTime start, LocalDateTime end);

    // 기간 내 이벤트 목록 (페이지네이션)
    Page<NoiseEvent> findByCreatedAtBetween(LocalDateTime start, LocalDateTime end, Pageable pageable);

    // 센서 기준 이벤트 조회 (통계 / 분석 용으로)
    List<NoiseEvent> findByNoiseSensor(NoiseSensor noiseSensor);

    // 모든 이벤트 집계/통계용
    List<NoiseEvent> findByCreatedAtBetween(LocalDateTime start, LocalDateTime end);
}
