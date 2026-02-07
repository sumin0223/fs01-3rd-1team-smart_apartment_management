package com.jjld.domain.noise.dto;

import com.jjld.domain.noise.entity.Enum.NoisePattern1;
import com.jjld.domain.noise.entity.Enum.ProcessStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NoiseEventListResponse {
    // 소음 이벤트 ID
    private Long noiseEventId;

    // 위치 정보
    private Integer upperHouseDong;
    private Integer upperHouseHo;

    private Integer lowerHouseDong;
    private Integer lowerHouseHo;

    // 발생 시간
    private LocalDateTime occurredAt;

    // 시간대 (주간 / 야간)
    private String timeZone;

    // 소음 강도(dB)
    private Integer soundLevel;

    // 소음 유형 (1차 분류)
    private NoisePattern1 noisePattern1;

    // 반복 횟수
    private Integer repeatCount;

    // 처리 상태 (PENDING / APPROVED / HOLD)
    private ProcessStatus status;

    // 즉시 처리 필요 여부 (우선순위 표시용)
    private Boolean urgentBreak;
}
