package com.jjld.domain.noise.service;

import com.jjld.domain.noise.dto.NoiseEventDetailResponse;
import com.jjld.domain.noise.dto.NoiseEventListResponse;
import com.jjld.domain.noise.dto.NoiseUrgentEventResponse;
import com.jjld.domain.noise.entity.Enum.ProcessStatus;
import com.jjld.domain.noise.entity.NoiseEventProcess;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface NoiseEventService {
    // 즉시 처리 필요/승인대기 이벤트 목록조회
    Page<NoiseEventProcess> findUrgentNoiseByStatus(Pageable pageable);

    // 소음 이벤트 승인 처리
    void approveNoiseEvent(Long noiseEventId, String adminMemo);

    // 소음 이벤트 보류 처리
    void holdNoiseEvent(Long noiseEventId, String adminMemo);

    // 상세 조회용 Response DTO 반환
    NoiseEventDetailResponse getNoiseEventDetailResponse(Long noiseEventId);

    // 즉시 처리 필요 소음 이벤트 목록 (DTO 반환)
    Page<NoiseUrgentEventResponse> getUrgentNoiseEventResponses(Pageable pageable);

    // 이벤트 목록 (전체/상태별/페이지네이션)
    Page<NoiseEventListResponse> getNoiseEventListResponses(ProcessStatus status, Pageable pageable
    );
}
