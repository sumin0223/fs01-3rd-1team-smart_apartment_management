package com.jjld.domain.entrancedoor.dto;


import com.jjld.domain.entrancedoor.entity.Enum.AccessType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GateAuthTEstRequest {
    // 인증 방식
    private AccessType accessType;

    // 동
    private Integer dong;

    // 호수
    private Integer ho;

    // 카드 UID
    private String cardUid;

    // 비밀번호
    private String password;
}
