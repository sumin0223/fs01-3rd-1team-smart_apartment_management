package com.jjld.domain.noise.service;

import com.jjld.domain.house.entity.House;
import com.jjld.domain.noise.dao.NoiseEventDAO;
import com.jjld.domain.noise.dto.NoiseEventDetailResponse;
import com.jjld.domain.noise.dto.NoiseEventListResponse;
import com.jjld.domain.noise.dto.NoiseUrgentEventResponse;
import com.jjld.domain.noise.entity.Enum.ProcessStatus;
import com.jjld.domain.noise.entity.NoiseEvent;
import com.jjld.domain.noise.entity.NoiseEventAnalysis;
import com.jjld.domain.noise.entity.NoiseEventProcess;
import com.jjld.domain.noise.entity.NoiseSensor;
import com.jjld.domain.noise.repository.NoiseEventProcessRepository;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class NoiseEventServiceImpl implements NoiseEventService {
    private final NoiseEventDAO noiseEventDAO;
    private final NoiseEventProcessRepository noiseEventProcessRepository;
    private final NoisePolicyService noisePolicyService;

    //-----목록-------
    @Override
    @Transactional(readOnly = true)
    public Page<NoiseEventListResponse> getNoiseEventListResponses(ProcessStatus status, Pageable pageable) {
        // 상태 조건이 있으면 필터, 없으면 전체
        Page<NoiseEventProcess> processPage =
                (status == null)
                        ? noiseEventDAO.findAllNoiseEvent(pageable)
                        : noiseEventDAO.findNoiseEventByStatus(status, pageable);
        // Entity → DTO 변환
        return processPage.map(this::toListResponse);
    }
    //  ------ 즉시처리 -------
    @Override
    @Transactional(readOnly = true)
    public Page<NoiseUrgentEventResponse> getUrgentNoiseEventResponses(Pageable pageable) {
        // 1. 즉시 처리 필요 이벤트(Process) 조회
        Page<NoiseEventProcess> processPage = noiseEventProcessRepository.findByUrgentBreakTrueAndStatus(ProcessStatus.PENDING, pageable);
        // 2. 엔티티 → DTO 변환
        return processPage.map(this::toUrgentResponse);
    }
    // -----소음이벤트 상세조회------
    @Override
    @Transactional(readOnly = true)
    public NoiseEventDetailResponse getNoiseEventDetailResponse(Long noiseEventId) {
        // 1. 서비스 호출을 통해 소음 이벤트 처리(Process) 정보 조회
        NoiseEventProcess process = noiseEventDAO.findNoiseEventDetail(noiseEventId);
        return toDetailResponse(process);
    }
    // 즉시처리필요/승인대기 이벤트 목록 조회
    @Override
    public Page<NoiseEventProcess> findUrgentNoiseByStatus(Pageable pageable) {
        return noiseEventProcessRepository
                .findByUrgentBreakTrueAndStatus(ProcessStatus.PENDING, pageable);
    }
    // 소음 이벤트 승인 처리 (이벤트 상태를 APPROVED로 변경 >> 관리자 메모 저장)
    @Override
    public void approveNoiseEvent(Long noiseEventId, String adminMemo) {
        NoiseEventProcess process =
                noiseEventDAO.findNoiseEventDetail(noiseEventId);
        // 승인 상태로 변경
        process.setStatus(ProcessStatus.APPROVED);
        // 관리자 메모 저장
        process.setAdminMemo(adminMemo);
    }
    // 소음 이벤트 보류 처리 (이벤트 상태를 HOLD로 변경 >> 관리자 메모 저장)
    @Override
    public void holdNoiseEvent(Long noiseEventId, String adminMemo) {
        NoiseEventProcess process =
                noiseEventDAO.findNoiseEventDetail(noiseEventId);
        // 보류 상태로 변경
        process.setStatus(ProcessStatus.HOLD);
        // 관리자 메모 저장
        process.setAdminMemo(adminMemo);
    }

    // 목록 조회용 DTO
    private NoiseEventListResponse toListResponse(NoiseEventProcess process) {
        NoiseEvent noiseEvent = process.getNoiseEvent();
        NoiseEventAnalysis analysis = noiseEvent.getNoiseEventAnalysis();
        NoiseSensor sensor = noiseEvent.getNoiseSensor();

        House upper = sensor.getUpperHouse();
        House lower = sensor.getLowerHouse();

        return NoiseEventListResponse.builder()
                .noiseEventId(noiseEvent.getNoiseEventId())
                // 발생 구간 (센서 기준)
                .upperHouseDong(upper.getHouseDong())
                .upperHouseHo(upper.getHouseHo())
                .lowerHouseDong(lower.getHouseDong())
                .lowerHouseHo(lower.getHouseHo())
                .occurredAt(noiseEvent.getCreatedAt())
                .timeZone(
                        noisePolicyService.isDayTime(
                                noiseEvent.getCreatedAt().toLocalTime()
                        ) ? "주간" : "야간"
                )
                .soundLevel(noiseEvent.getSoundLevel())
                .noisePattern1(analysis.getNoisePattern1())
                .repeatCount(analysis.getRepeatCount())
                .status(process.getStatus())
                .urgentBreak(process.getUrgentBreak())
                .build();
    }

    // 즉시 처리 필요 목록 DTO
    private NoiseUrgentEventResponse toUrgentResponse(NoiseEventProcess process) {
        NoiseEvent noiseEvent = process.getNoiseEvent();
        NoiseEventAnalysis analysis = noiseEvent.getNoiseEventAnalysis();
        NoiseSensor sensor = noiseEvent.getNoiseSensor();

        House upper = sensor.getUpperHouse();
        House lower = sensor.getLowerHouse();

        return NoiseUrgentEventResponse.builder()
                .noiseEventId(noiseEvent.getNoiseEventId())
                .upperHouseDong(upper.getHouseDong())
                .upperHouseHo(upper.getHouseHo())
                .lowerHouseDong(lower.getHouseDong())
                .lowerHouseHo(lower.getHouseHo())
                .occurredAt(noiseEvent.getCreatedAt())
                .timeZone(
                        noisePolicyService.isDayTime(
                                noiseEvent.getCreatedAt().toLocalTime()
                        ) ? "주간" : "야간"
                )
                .soundLevel(noiseEvent.getSoundLevel())
                .noisePattern1(analysis.getNoisePattern1())
                .repeatCount(analysis.getRepeatCount())
                .status(process.getStatus())
                .build();
    }

    // 상세 조회용 DTO
    private NoiseEventDetailResponse toDetailResponse(NoiseEventProcess process) {
        NoiseEvent noiseEvent = process.getNoiseEvent();
        NoiseEventAnalysis analysis = noiseEvent.getNoiseEventAnalysis();
        NoiseSensor sensor = noiseEvent.getNoiseSensor();
        House upper = sensor.getUpperHouse();
        House lower = sensor.getLowerHouse();

        return NoiseEventDetailResponse.builder()
                .noiseEventId(noiseEvent.getNoiseEventId())
                // 위치 정보
                .upperHouseDong(upper.getHouseDong())
                .upperHouseHo(upper.getHouseHo())
                .lowerHouseDong(lower.getHouseDong())
                .lowerHouseHo(lower.getHouseHo())
                // 발생 정보
                .occurredAt(noiseEvent.getCreatedAt())
                .soundLevel(noiseEvent.getSoundLevel())
                // 센서 정보
                .sensorType(sensor.getSensorType().name())
                // 시간대
                .timeZone(
                        noisePolicyService.isDayTime(
                                noiseEvent.getCreatedAt().toLocalTime()
                        ) ? "주간" : "야간"
                )
                // 소음 패턴
                .noisePattern1(analysis.getNoisePattern1())
                .noisePattern2(analysis.getNoisePattern2())
                // 반복 횟수
                .repeatCount(analysis.getRepeatCount())
                // 정책 위반 여부
                .policyBreak(analysis.getPolicyBreak())
                .analysisNote(analysis.getAnalysisNote())
                // 처리 상태
                .status(process.getStatus())
                .adminMemo(process.getAdminMemo())
                .build();
    }
}
