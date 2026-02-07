package com.jjld.domain.noise.dto;

import com.jjld.domain.noise.entity.Enum.NoisePattern1;
import com.jjld.domain.noise.entity.Enum.SensorType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NoiseStatisticsResponse {
    // 시간대별 소음 발생 건수 (0~23)
    private Map<Integer, Long> noiseCountByHour;

    // 시간대별 정책 위반 건수 (0~23)
    private Map<Integer, Long> policyBreakCountByHour;

    // 센서 신호 유형 분포 (원 그래프)
    private Map<SensorType, Long> sensorTypeDistribution;

    // 소음 패턴 발생 빈도 (레이더 차트)
    private Map<NoisePattern1, Long> noisePatternDistribution;
}
