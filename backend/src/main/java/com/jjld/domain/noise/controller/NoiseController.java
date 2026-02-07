package com.jjld.domain.noise.controller;

import com.jjld.domain.house.entity.House;
import com.jjld.domain.noise.dto.*;
import com.jjld.domain.noise.entity.NoiseEvent;
import com.jjld.domain.noise.entity.NoiseEventAnalysis;
import com.jjld.domain.noise.entity.NoiseEventProcess;
import com.jjld.domain.noise.entity.NoiseSensor;
import com.jjld.domain.noise.service.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Tag(name = "noise-controller", description = "층간소음 이벤트 관리 API")
@RestController
@RequestMapping("/noise/api")
@RequiredArgsConstructor
public class NoiseController {
    private final NoiseFlowService noiseFlowService;
    private final NoiseDashboardService noiseDashboardService;
    private final NoiseEventService noiseEventService;
    private final NoiseStatisticsService noiseStatisticsService;
    // 소음 이벤트 수신 API - 센서에서 소음발생시 호출
    @PostMapping("/event/add")
    @Operation(summary = "층간소음 이벤트 등록", description = "센서에서 감지된 소음 이벤트를 등록한다.")
    public ResponseEntity<String> receiveNoiseEvent(
            @RequestParam Long sensorId,
            @RequestParam int soundLevel) {
        noiseFlowService.receiveNoiseEvent(sensorId, soundLevel);
        return ResponseEntity.ok("소음 이벤트가 정상적으로 처리되었습니다.");
    }

    @GetMapping("/dashboard")
    @Operation(
            summary = "대시보드 상단 요약 카드 조회",
            description = "오늘 발생 이벤트 수, 정책 위반 의심 수, 승인 대기 수, 현재 시간대(주/야)를 조회한다."
    )
    public ResponseEntity<NoiseDashboardResponse> getDashboardSummary() {
        return ResponseEntity.ok(noiseDashboardService.getDashboard());
    }

    @GetMapping("/urgent")
    @Operation(summary = "즉시 처리 필요 소음 이벤트 목록 조회", description = "정책 위반 의심 + 승인 대기 상태의 소음 이벤트 목록을 조회한다.")
    public ResponseEntity<Page<NoiseUrgentEventResponse>> getUrgentNoiseEvents(@PageableDefault(size = 10) Pageable pageable) {
        return ResponseEntity.ok(
                noiseEventService.getUrgentNoiseEventResponses(pageable)
        );
    }
    // 이벤트 상세 조회 API
    @GetMapping("/event/{noiseEventId}")
    @Operation(summary = "소음 이벤트 상세 조회", description = "선택한 소음 이벤트의 상세 정보(분석 결과, 상태, 관리자 메모 등)를 조회한다.")
    public ResponseEntity<NoiseEventDetailResponse> getNoiseEventDetail(@PathVariable Long noiseEventId) {
        return ResponseEntity.ok(noiseEventService.getNoiseEventDetailResponse(noiseEventId));
    }

    // 이벤트 승인API
    @PostMapping("/event/{noiseEventId}/approve")
    @Operation(summary = "소음 이벤트 승인 처리", description = "선택한 소음 이벤트를 승인 상태로 변경하고 관리자 메모를 저장한다.")
    public ResponseEntity<Void> approveNoiseEvent(@PathVariable Long noiseEventId, @RequestBody NoiseEventDecisionRequest request) {
        // 서비스에 승인 처리 위임
        noiseEventService.approveNoiseEvent(noiseEventId, request.getAdminMemo());
        return ResponseEntity.ok().build();
    }

    // 이벤트 보류  API
    @PostMapping("/event/{noiseEventId}/hold")
    @Operation(summary = "소음 이벤트 보류 처리", description = "선택한 소음 이벤트를 보류 상태로 변경하고 관리자 메모를 저장한다.")
    public ResponseEntity<Void> holdNoiseEvent(@PathVariable Long noiseEventId, @RequestBody NoiseEventDecisionRequest request) {
        // 서비스에 보류 처리 위임
        noiseEventService.holdNoiseEvent(noiseEventId, request.getAdminMemo());
        return ResponseEntity.ok().build();
    }
    // 이벤트 통계 API
    @GetMapping("/statistics")
    @Operation(summary = "소음 통계/그래프 데이터 조회", description = "시간대별 소음 발생, 정책 위반, 센서 유형 분포, 소음 패턴 통계를 조회한다.")
    public ResponseEntity<NoiseStatisticsResponse> getNoiseStatistics(@RequestParam(required = false) LocalDate date) {
        // date가 없으면 서비스에서 오늘 기준 처리
        return ResponseEntity.ok(noiseStatisticsService.getStatistics(date));
    }
}
