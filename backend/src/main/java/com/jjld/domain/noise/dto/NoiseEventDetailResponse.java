package com.jjld.domain.noise.dto;

import com.jjld.domain.noise.entity.Enum.NoisePattern1;
import com.jjld.domain.noise.entity.Enum.NoisePattern2;
import com.jjld.domain.noise.entity.Enum.ProcessStatus;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class NoiseEventDetailResponse {
    // 소음 이벤트 ID
    private Long noiseEventId;

    // 위치 정보
    private Integer upperHouseDong;
    private Integer upperHouseHo;

    private Integer lowerHouseDong;
    private Integer lowerHouseHo;

    // 발생 시간
    private LocalDateTime occurredAt;

    // 센서 유형
    private String sensorType;

    // 소음 추정 강도 (dB)
    private Integer soundLevel;

    // 시간대 (주간 / 야간)
    private String timeZone;

    // 소음 패턴 분류
    private NoisePattern1 noisePattern1;
    private NoisePattern2 noisePattern2;

    // 반복 발생 횟수
    private Integer repeatCount;

    // 정책 위반 의심 여부
    private Boolean policyBreak;

    // 정책 위반 사유 설명 문구
    private String analysisNote;

    // 처리 상태
    private ProcessStatus status;

    // 관리자 메모
    private String adminMemo;
}
