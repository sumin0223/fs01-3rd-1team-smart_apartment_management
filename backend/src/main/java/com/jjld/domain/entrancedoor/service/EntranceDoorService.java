package com.jjld.domain.entrancedoor.service;

import com.jjld.domain.admin.entity.Admin;
import com.jjld.domain.entrancedoor.dto.EntranceGateLogResponse;
import com.jjld.domain.entrancedoor.dto.EntranceGateLogSearchCond;
import com.jjld.domain.entrancedoor.dto.EntranceGateResponse;
import com.jjld.domain.entrancedoor.dto.GateAuthTEstRequest;
import com.jjld.domain.entrancedoor.entity.EntranceOpenRequest;
import com.jjld.domain.entrancedoor.entity.Enum.AccessType;
import com.jjld.domain.entrancedoor.entity.Enum.FailReason;
import com.jjld.domain.house.entity.House;
import org.springframework.data.domain.Page;

import java.util.List;

public interface EntranceDoorService {
    // 세대 동 조회
    List<EntranceGateResponse> findAll();

    // 공동현관 출입 로그 페이징 조회
    Page<EntranceGateLogResponse> search(EntranceGateLogSearchCond cond, int page, int size);

    // 공동현관 출입 기록
    void gateLogInsert(AccessType type, boolean success, House house, Admin admin, FailReason failReason);

    // 공동현관 관리자 호출
    Long createAdminCall(Long houseId);

    // 공동현관 관리자 호출 미처리 목록
    List<EntranceOpenRequest> getPendingCalls();

    // 공동현관 관리자 호출 승인 처리
    void approvalCall(Long requestId, Long adminId);

    // 공동현관 관리자 호출 미승인 처리
    void rejectCall(Long requestId, Long adminId);

    // 공동현관 카드 조회
    boolean authenticateCard(String cardUid, House house);

    // 공동현관 비밀번호 조회
    boolean authenticatePass(House house, String entrancePass);

    // 공동현관 인증 처리
    boolean authenticateTest(GateAuthTEstRequest request);
}
