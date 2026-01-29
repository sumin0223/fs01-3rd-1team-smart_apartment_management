package com.jjld.domain.complex.controller;

import com.jjld.domain.complex.dto.ComplexReq;
import com.jjld.domain.complex.dto.ComplexRes;
import com.jjld.domain.complex.service.ComplexService;
import com.jjld.global.response.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/complex/api")
@RequiredArgsConstructor
public class ComplexController {
    private final ComplexService complexService;

    // 단지 정보 생성
    @PostMapping("/{adminId}")
    public ResponseEntity<?> createComplex(
            @PathVariable("adminId") Long adminId,
            @Valid @RequestBody ComplexReq complexReq
    ) {
        complexService.createComplex(adminId, complexReq);
        return ResponseEntity.ok(ApiResponse.success("단지 정보 생성을 성공했습니다."));
    }

    // 단지 정보 조회
    @GetMapping
    public ResponseEntity<?> getComplex() {
        ComplexRes response = complexService.getComplex();
        return ResponseEntity.ok(ApiResponse.success(response));
    }
}
