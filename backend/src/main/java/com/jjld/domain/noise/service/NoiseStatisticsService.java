package com.jjld.domain.noise.service;

import com.jjld.domain.noise.dto.NoiseStatisticsResponse;

import java.time.LocalDate;

public interface NoiseStatisticsService {
    // 특정날짜의 소음통계 조회(그래프용
    NoiseStatisticsResponse getStatistics(LocalDate date);
}
