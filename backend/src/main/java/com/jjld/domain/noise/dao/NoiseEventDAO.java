package com.jjld.domain.noise.dao;

import com.jjld.domain.noise.entity.Enum.ProcessStatus;
import com.jjld.domain.noise.entity.NoiseEventProcess;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

// 소음 이벤트 목록/상세/처리대상조회 DAO -> 클릭하는 데이터 담당
public interface NoiseEventDAO {
    // 즉시 처리 필요+승인 대기중 이벤트 분석 목록 조회
    // @param pageable 페이지 정보(10개로 임시 고정쓰)
    Page<NoiseEventProcess> findUrgentNoiseEvent(Pageable pageable);

    // 상태별 소음 이벤트 목록 조회
    // @param status   WAITING / APPROVED / HOLD 등
    // @param pageable 페이지 정보
    Page<NoiseEventProcess> findNoiseEventByStatus(
            ProcessStatus status,
            Pageable pageable);

    // 소음 이벤트 단건 상세 조회
    // @param noiseEventId 소음 이벤트 ID
    NoiseEventProcess findNoiseEventDetail(Long noiseEventId);

    // 전체 소음 이벤트 목록 조회 (상태 무관)
    Page<NoiseEventProcess> findAllNoiseEvent(Pageable pageable);

}
