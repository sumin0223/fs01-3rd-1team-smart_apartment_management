package com.jjld.global.exception;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.jjld.global.response.ApiResponse;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // 우리가 만들 비즈니스 예외 처리
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<?> handleBusinessException(BusinessException e) {
        ErrorCode errorCode = e.getErrorCode();

        return ResponseEntity
                .status(errorCode.getStatus())
                .body(ApiResponse.error(errorCode.getCode(), e.getCustomMessage()));
    }

    // @Valid 검증 실패
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationException(MethodArgumentNotValidException e) {
        String message = e.getBindingResult()
                .getFieldErrors()
                .get(0)
                .getDefaultMessage();

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ApiResponse.error("VALIDATION_FAILED", message));
        // 지금 코드는 첫 번째 오류만 볼 수 있음
        // 모든 오류를 볼 수 있게 할 수 있지만 메시지가 길어질 수 있음
        // 상의 필요
    }

    // JSON / ENUM 형식 오류
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> handleJsonParseException(HttpMessageNotReadableException e) {
        if (e.getCause() instanceof InvalidFormatException ex &&
                ex.getTargetType().isEnum()) {

            return ResponseEntity.badRequest()
                    .body(ApiResponse.error("INVALID_ENUM_VALUE",
                            "요청한 enum 값이 올바르지 않습니다."));
        }

        return ResponseEntity.badRequest()
                .body(ApiResponse.error("INVALID_JSON",
                        "요청 본문 형식이 올바르지 않습니다."));
        // .status(HttpStatus.BAD_REQUEST)와 차이
        // num으로 관리할 경우 .status()가 좋음
        // 하지만 고정 에러인 경우 .badRequest()쓴다고 함
        // -> 에러코드 enum 기반 = status()
        // -> 고정 에러 = badRequest()
    }

    // 잘못된 정렬 필드
    @ExceptionHandler(PropertyReferenceException.class)
    public ResponseEntity<ApiResponse<?>> handleSortError(PropertyReferenceException e) {
        return ResponseEntity.badRequest().body(
                ApiResponse.error("INVALID_SORT_FIELD", "존재하지 않는 정렬 필드입니다.")
        );
    }

    // 잘못된 페이지 요청
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiResponse<?>> handlePageError(IllegalArgumentException e) {
        return ResponseEntity.badRequest().body(
                ApiResponse.error("INVALID_PAGE_REQUEST", "페이지 요청 값이 올바르지 않습니다.")
        );
    }

    // 모든 예외의 마지막 처리
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception e) {
        e.printStackTrace(); // 서버 로그용

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiResponse.error(
                        ErrorCode.INTERNAL_SERVER_ERROR.getCode(),
                        ErrorCode.INTERNAL_SERVER_ERROR.getMessage()));
    }
}
