package com.jjld.domain.noise.service;

import com.jjld.domain.noise.dao.NoiseStatisticsDAO;
import com.jjld.domain.noise.dto.NoiseStatisticsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class NoiseStatisticsServiceImpl implements NoiseStatisticsService {
    private final NoiseStatisticsDAO noiseStatisticsDAO;
    @Override
    public NoiseStatisticsResponse getStatistics(LocalDate date) {
        // 1. 하루기준 조회범위계산
        LocalDateTime start = date.atStartOfDay();
        LocalDateTime end = date.plusDays(1).atStartOfDay();
        return NoiseStatisticsResponse.builder()
                // 시간대별 전체 소음 발생 건수
                .noiseCountByHour(noiseStatisticsDAO.countNoiseEventByHour(start, end))
                // 시간대별 정책 위반 건수
                .policyBreakCountByHour(noiseStatisticsDAO.countPolicyBreakByHour(start, end))
                // 센서 신호 유형 분포 (원 그래프)
                .sensorTypeDistribution(noiseStatisticsDAO.countSensorType(start, end))
                // 소음 패턴 발생 빈도 (레이더 차트)
                .noisePatternDistribution(noiseStatisticsDAO.countNoisePattern(start, end))
                .build();
    }
}
