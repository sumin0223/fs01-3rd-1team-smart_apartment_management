package com.jjld.domain.noise.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NoiseEventDecisionRequest {
    // 관리자 메모 (승인/보류 시 입력)
    private String adminMemo;
}
