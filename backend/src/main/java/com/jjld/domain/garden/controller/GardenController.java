package com.jjld.domain.garden.controller;

import com.jjld.domain.garden.dto.GardenReq;
import com.jjld.domain.garden.entity.Garden;
import com.jjld.domain.garden.service.GardenService;
import com.jjld.global.response.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/garden/api")
@RequiredArgsConstructor
public class GardenController {
    private final GardenService gardenService;

    // 정원 관리 구역 생성
    @PostMapping
    public ResponseEntity<?> createGarden(@Valid @RequestBody GardenReq gardenReq) {
        gardenService.createGarden(gardenReq);
        return ResponseEntity.ok(ApiResponse.success("구역 추가를 성공했습니다."));
    }
}
