package com.jjld.domain.noise.dto;

import com.jjld.domain.noise.entity.Enum.NoisePattern1;
import com.jjld.domain.noise.entity.Enum.ProcessStatus;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

// 즉시처리필요 소음이벤트목록 응답DTO
@Data
@Builder
public class NoiseUrgentEventResponse {
    // 소음 이벤트 ID
    private Long noiseEventId;

    // 위치(동.호수 -house 추출)
    private Integer upperHouseDong;
    private Integer upperHouseHo;

    private Integer lowerHouseDong;
    private Integer lowerHouseHo;

    // 시간대 (주/야
    private String timeZone;

    // 소음추정강도(dB)
    private Integer soundLevel;

    //발생시간
    private LocalDateTime occurredAt;

    // 1차분류(시스템
    private NoisePattern1 noisePattern1;

    // 반복횟수
    private Integer repeatCount;

    //처리상태
    private ProcessStatus status;
}
