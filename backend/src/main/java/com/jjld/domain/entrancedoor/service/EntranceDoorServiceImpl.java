package com.jjld.domain.entrancedoor.service;

import com.jjld.domain.admin.entity.Admin;
import com.jjld.domain.admin.repository.AdminRepository;
import com.jjld.domain.entrancedoor.dto.EntranceGateLogResponse;
import com.jjld.domain.entrancedoor.dto.EntranceGateLogSearchCond;
import com.jjld.domain.entrancedoor.dto.EntranceGateResponse;
import com.jjld.domain.entrancedoor.dto.GateAuthTEstRequest;
import com.jjld.domain.entrancedoor.entity.EntranceDoor;
import com.jjld.domain.entrancedoor.entity.EntranceGateLog;
import com.jjld.domain.entrancedoor.entity.EntranceOpenRequest;
import com.jjld.domain.entrancedoor.entity.Enum.AccessType;
import com.jjld.domain.entrancedoor.entity.Enum.DoorCallStatus;
import com.jjld.domain.entrancedoor.entity.Enum.FailReason;
import com.jjld.domain.entrancedoor.repository.EntranceDoorRepository;
import com.jjld.domain.entrancedoor.repository.EntranceGateLogRepository;
import com.jjld.domain.entrancedoor.repository.EntranceOpenRequestRepository;
import com.jjld.domain.house.entity.EntranceCard;
import com.jjld.domain.house.entity.Enum.CardStatus;
import com.jjld.domain.house.entity.House;
import com.jjld.domain.house.repository.EntranceCardRepository;
import com.jjld.domain.house.repository.HouseRepository;
import com.jjld.global.exception.ErrorCode;
import com.jjld.global.exception.businessexceptions.NotFoundException;
import com.jjld.domain.entrancedoor.specification.EntranceGateLogSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EntranceDoorServiceImpl implements EntranceDoorService{
    private final EntranceDoorRepository doorRepository;
    private final EntranceGateLogRepository gateLogRepository;
    private final HouseRepository houseRepository;
    private final AdminRepository adminRepository;
    private final EntranceCardRepository cardRepository;
    private final EntranceOpenRequestRepository openRequestRepository;
    private final PasswordEncoder passwordEncoder;

    // 세대 동 조회
    @Override
    public List<EntranceGateResponse> findAll() {
        List<EntranceDoor> doors = doorRepository.findAll();

        return doors.stream()
                .map(door -> {
                    // 최근 출입시간 조회
                    LocalDateTime lastAccess = gateLogRepository.findTopByHouse_HouseDongAndOutcomeTrueOrderByAccessedAtDesc(door.getHouseDong())
                            .map(EntranceGateLog::getAccessedAt)
                            .orElse(null);

                    return new EntranceGateResponse(
                            door.getHouseDong(),
                            door.getStatus().name(),
                            lastAccess
                    );
                })
                .toList();
    }

    // 공동현관 출입 로그 페이징 조회
    @Override
    public Page<EntranceGateLogResponse> search(EntranceGateLogSearchCond cond, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("accessedAt").descending());

        Specification<EntranceGateLog> spec = Specification.allOf(
                EntranceGateLogSpecification.equalHouseDong(cond.getHouseDong()),
                EntranceGateLogSpecification.equalAccessType(cond.getAccessType())
        );

        Page<EntranceGateLog> gateLogPage = gateLogRepository.findAll(spec, pageable);
        if(gateLogPage == null){
            throw new NotFoundException(ErrorCode.NOT_FOUND, "해당 페이지를 찾을 수 없습니다.");
        }

        return gateLogPage.map(log -> {
            String adminName = null;

            // 관리자를 호출한 경우만 adminName 조회
            if(log.getAccessType() == AccessType.ADMIN_CALL && log.getAdmin() != null){
                Admin admin = adminRepository.findByAdminLoginId(String.valueOf(log.getAccessLogId())).orElse(null);
                if(admin != null){
                    adminName = admin.getAdminName();
                }
            } return new EntranceGateLogResponse(log, adminName);
        });
    }

    // 공동현관 관리자 호출 요청
    public Long createAdminCall(Long houseId){
        House house = houseRepository.findById(houseId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.HOUSE_NOT_FOUND,"세대를 찾을 수 없습니다."));

        EntranceOpenRequest request = EntranceOpenRequest.builder()
                .house(house)
                .requestedAt(LocalDateTime.now())
                .status(DoorCallStatus.PENDING)
                .build();

        openRequestRepository.save(request);

        return request.getRequestId();
    }

    // 공동현관 관리자 호출 미처리 목록
    @Override
    public List<EntranceOpenRequest> getPendingCalls(){

        return openRequestRepository.findByStatus(DoorCallStatus.PENDING);
    }

    // 공동현관 관리자 호출 승인
    @Override
    public void approvalCall(Long requestId, Long adminId){
        EntranceOpenRequest request = openRequestRepository.findById(requestId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.ALARM_NOT_FOUND, "호출 요청이 없습니다."));

        Admin admin = adminRepository.findByAdminLoginId(String.valueOf(adminId))
                .orElseThrow(() -> new NotFoundException(ErrorCode.ADMIN_NOT_FOUND, "관리자를 찾을 수 없습니다."));

        request.setStatus(DoorCallStatus.APPROVED);
        request.setProcessedAt(LocalDateTime.now());
        request.setAdmin(admin);

        gateLogInsert(AccessType.ADMIN_CALL,true, null, request.getAdmin(), FailReason.NONE);
    }

    // 관리자 호출 거절
    @Override
    public void rejectCall(Long requestId, Long adminId){
        EntranceOpenRequest request = openRequestRepository.findById(requestId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.ALARM_NOT_FOUND, "호출 요청이 없습니다."));

        Admin admin = adminRepository.findByAdminLoginId(String.valueOf(adminId))
                .orElseThrow(() -> new NotFoundException(ErrorCode.ADMIN_NOT_FOUND, "관리자를 찾을 수 없습니다."));

        request.setStatus(DoorCallStatus.REJECTED);
        request.setProcessedAt(LocalDateTime.now());
        request.setAdmin(admin);


        gateLogInsert(AccessType.ADMIN_CALL,false, null, admin, FailReason.ADMIN_REJECT);
    }

    // 공동현관 출입 인증/기록
 // 101#1234
    // 출입 기록
    @Override
    public void gateLogInsert(AccessType type, boolean success, House house, Admin admin, FailReason failReason) {

        EntranceGateLog log = EntranceGateLog.builder()
                .accessType(type)
                .outcome(success)
                .house(house)
                .admin(admin)
                .failReason(failReason)
                .accessedAt(LocalDateTime.now())
                .build();

        gateLogRepository.save(log);
    }

    // 출입 인증 카드
    public boolean authenticateCard(String cardUid, House house) {

        // 카드 정보 조회
        EntranceCard card = cardRepository.findByCardUid(cardUid).orElse(null);

        // DB에 저장된 카드인지
        if(card == null){
            gateLogInsert(AccessType.RESIDENT_CARD, false, house, null, FailReason.INVALID_CARD);
            return false;
        }

        // 카드 활성화 상태
        if(card.getStatus() != CardStatus.ACTIVE){
            gateLogInsert(AccessType.RESIDENT_CARD, false, house, null, FailReason.EXPIRED_CARD);
            return false;
        }

        gateLogInsert(AccessType.RESIDENT_CARD, true, card.getHouse(), null, FailReason.NONE);

        return true;
    }

    // 출입 인증 비밀번호
    // 키패드 연동 시
    // public boolean authenticatePass(String topic, String input)
    public boolean authenticatePass(House house, String entrancePass){
//        String dong = topic.split("/")[1];
//        String[] parts = input.split("#");
//
//        if(parts.length != 2){
//            gateLogInsert(AccessType.RESIDENT_PASSWORD, false, null, null);
//            return false;
//        }


        if(!passwordEncoder.matches(entrancePass, house.getEntrancePass())){
            gateLogInsert(AccessType.RESIDENT_PASSWORD, false, house, null, FailReason.WRONG_PASSWORD);
            return false;
        }

        gateLogInsert(AccessType.RESIDENT_PASSWORD, true, house, null, FailReason.NONE);

        return true;
    }

    // 인증 테스트
    public boolean authenticateTest(GateAuthTEstRequest request){
        House house = houseRepository.findByHouseDongAndHouseHo(request.getDong(), request.getHo());

        if(house == null){
            throw new NotFoundException(ErrorCode.HOUSE_NOT_FOUND, "세대를 찾을 수 없습니다.");
        }

        switch (request.getAccessType()){
            case RESIDENT_CARD:
                return authenticateCard(request.getCardUid(), house);
            case RESIDENT_PASSWORD:
                return authenticatePass(house, request.getPassword());
            case ADMIN_CALL:
                createAdminCall(house.getHouseId());
                return true;
        }
        return false;
    }

}
