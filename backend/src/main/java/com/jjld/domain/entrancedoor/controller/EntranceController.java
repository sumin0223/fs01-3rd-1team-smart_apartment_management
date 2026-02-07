package com.jjld.domain.entrancedoor.controller;

import com.jjld.domain.entrancedoor.dto.EntranceGateLogResponse;
import com.jjld.domain.entrancedoor.dto.EntranceGateLogSearchCond;
import com.jjld.domain.entrancedoor.dto.EntranceGateResponse;
import com.jjld.domain.entrancedoor.dto.GateAuthTEstRequest;
import com.jjld.domain.entrancedoor.entity.EntranceOpenRequest;
import com.jjld.domain.entrancedoor.service.EntranceDoorService;
import com.jjld.global.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/entrance/api")
@RequiredArgsConstructor
public class EntranceController {
    private final EntranceDoorService service;

    // 세대 동 조회
    @GetMapping("/dong/list")
    @Operation(summary = "세대별 동 현관 상태 조회")
    public ResponseEntity<?> getDongList(){
        List<EntranceGateResponse> dongList = service.findAll();

        return ResponseEntity.ok(
                ApiResponse.success(dongList)
        );
    }

    // 공동현관 출입 로그 페이징
    @GetMapping("/log")
    @Operation(summary = "공동현관 출입 로그 페이징")
    public Page<EntranceGateLogResponse> search(
            EntranceGateLogSearchCond cond,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ){
        return service.search(cond, page, size);
    }

    // 출입 인증 테스트
    @PostMapping("/auth")
    @Operation(summary = "공동현관 출입 인증 테스트")
    public ResponseEntity<?> gateTest(
            @RequestBody GateAuthTEstRequest request
            ){
        boolean result = service.authenticateTest(request);

        return ResponseEntity.ok(
                ApiResponse.success(result)
        );
    }
}
