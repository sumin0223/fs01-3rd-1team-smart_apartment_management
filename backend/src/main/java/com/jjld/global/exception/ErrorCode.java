package com.jjld.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

// 모든 에러 정보는 여기서 관리합니다.
// 공통 밑으로 도메인별로 작성하시면 됩니다.
// 상태코드, 에러코드, 메시지를 한 번에 관리할 수 있으며
// 프론트와 협업할 때 문서 역할도 할 수 있습니다.
@Getter
@AllArgsConstructor
public enum ErrorCode {
    // HttpStatus 뒤에 쓸 값들 예시 --------------------------------------------------
    //
    // 400번대 - 클라이언트 측 실수
    // 400 BAD_REQUEST ------------ 요청 형식/값이 잘못됨 (JSON 형식 오류, 필수값 누락)
    // 401 UNAUTHORIZED ----------- 인증 안 됨 (로그인 안 한 사용자가 접근)
    // 403 FORBIDDEN -------------- 인증은 됐지만 권한 없음 (일반 사용자가 관리자 API 호출)
    // 404 NOT_FOUND -------------- 리소스 없음 (존재하지 않는 사용자 조회)
    // 409 CONFLICT --------------- 데이터 충돌 (중복 아이디 회원가입)
    // 422 UNPROCESSABLE_ENTITY --- 값은 있지만 규칙 위반 (비밀번호 길이 부족)
    //
    // 500번대 - 서버 측 실수
    // 500 INTERNAL_SERVER ------- 서버 내부 오류 (NullPointer, DB 장애)
    // 503 SERVICE_UNAVAILABLE --- 서버 일시적 장애 (서버 점검 중)
    // -----------------------------------------------------------------------------

    // 공통
    INVALID_REQUEST(HttpStatus.BAD_REQUEST, "INVALID_REQUEST", "잘못된 요청입니다."),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "INTERNAL_SERVER_ERROR", "서버 오류가 발생했습니다."),

    // Admin
    ADMIN_NOT_FOUND(HttpStatus.NOT_FOUND, "ADMIN_NOT_FOUND", "관리자를 찾을 수 없습니다."),
    DUPLICATE_ADMIN_LOGIN_ID(HttpStatus.CONFLICT, "DUPLICATE_ADMIN_LOGIN_ID", "이미 사용 중인 관리자 아이디입니다."),
    PASSWORD_MISMATCH(HttpStatus.BAD_REQUEST, "PASSWORD_MISMATCH", "비밀번호가 일치하지 않습니다."),


    // Alarm
    ALARM_NOT_FOUND(HttpStatus.NOT_FOUND, "ALARM_NOT_FOUND", "알람을 찾을 수 없습니다."),

    // CarGate
    CAR_GATE_NOT_FOUND(HttpStatus.NOT_FOUND, "CAR_GATE_NOT_FOUND", "차량 출입구를 찾을 수 없습니다."),

    // Complaint
    COMPLAINT_NOT_FOUND(HttpStatus.NOT_FOUND, "COMPLAINT_NOT_FOUND", "민원을 찾을 수 없습니다."),

    // DoorGate
    DOOR_GATE_NOT_FOUND(HttpStatus.NOT_FOUND, "DOOR_GATE_NOT_FOUND", "공동 현관을 찾을 수 없습니다."),

    // Elevator
    ELEVATOR_NOT_FOUND(HttpStatus.NOT_FOUND, "ELEVATOR_NOT_FOUND", "엘리베이터를 찾을 수 없습니다."),

    // Garden
    GARDEN_NOT_FOUND(HttpStatus.NOT_FOUND, "GARDEN_NOT_FOUND", "정원을 찾을 수 없습니다."),

    // House
    HOUSE_NOT_FOUND(HttpStatus.NOT_FOUND, "HOUSE_NOT_FOUND", "세대를 찾을 수 없습니다."),

    // Noise
    NOISE_NOT_FOUND(HttpStatus.NOT_FOUND, "NOISE_NOT_FOUND", "소음을 찾을 수 없습니다."),

    // Notice
    NOTICE_NOT_FOUND(HttpStatus.NOT_FOUND, "NOTICE_NOT_FOUND", "공지 사항을 찾을 수 없습니다."),

    // ParkingFee
    PARKING_FEE_NOT_FOUND(HttpStatus.NOT_FOUND, "PARKING_FEE_NOT_FOUND", "주차 요금을 찾을 수 없습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
