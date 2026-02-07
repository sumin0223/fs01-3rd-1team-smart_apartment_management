package com.jjld.domain.noise.dao;

import com.jjld.domain.noise.entity.Enum.ProcessStatus;
import com.jjld.domain.noise.entity.NoiseEvent;
import com.jjld.domain.noise.entity.NoiseEventProcess;
import com.jjld.domain.noise.repository.NoiseEventProcessRepository;
import com.jjld.domain.noise.repository.NoiseEventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
// repo에서 조회한 데이터 관리자 화면으로 반환 / 처리로직은 service(승인, 보류 ..)
@Repository
@RequiredArgsConstructor
public class NoiseEventDAOImpl implements NoiseEventDAO {
    private final NoiseEventProcessRepository noiseEventProcessRepository;
    private final NoiseEventRepository noiseEventRepository;
    // 즉시 처리 필요/승인대기중인 이벤트
    @Override
    public Page<NoiseEventProcess> findUrgentNoiseEvent(Pageable pageable) {
        return noiseEventProcessRepository
                .findByUrgentBreakTrueAndStatus(
                        ProcessStatus.PENDING,
                        pageable
                );
    }
    // 상태별 소음 이벤트(전체.승인ㄴ필요.처리완료 필터)
    @Override
    public Page<NoiseEventProcess> findNoiseEventByStatus(ProcessStatus status, Pageable pageable) {
        // 상태별 목록 조회
        return noiseEventProcessRepository.findByStatus(status, pageable);
    }
    // 이벤트 상세 조회(상세화면에서 보는 정보 기준- NoiseEvent>NoiseEventProcess 연결)
    @Override
    public NoiseEventProcess findNoiseEventDetail(Long noiseEventId) {
        // 이벤트 처리 정보 조회
        return noiseEventProcessRepository
                .findByNoiseEvent_NoiseEventId(noiseEventId)
                .orElseThrow(() ->
                        new IllegalArgumentException("소음 이벤트 처리 정보가 존재하지 않습니다. (id = " + noiseEventId)
                );
    }
    // 전체 소음 이벤트목록 조회
    @Override
    public Page<NoiseEventProcess> findAllNoiseEvent(Pageable pageable) {
        return noiseEventProcessRepository.findAll(pageable);
    }
}
