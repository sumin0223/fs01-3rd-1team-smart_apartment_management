package com.jjld.domain.complex.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ComplexReq {

    @NotBlank(message = "단지명 설정은 필수입니다.")
    private String name;

    @NotNull(message = "총 세대수 설정은 필수입니다.")
    @Min(value = 1, message = "총 세대수는 1 이상이어야 합니다.")
    private Integer totalHouseholds;

    @NotBlank(message = "주소 설정은 필수입니다.")
    private String address;

    @NotBlank(message = "대표 전화번호는 필수입니다.")
    @Pattern(regexp = "^\\d{11}$", message = "전화번호는 숫자 11자리여야 합니다.")
    private String phoneNumber;

    @NotBlank(message = "대표 이메일은 필수입니다.")
    @Email(message = "올바른 이메일 형식이 아닙니다.")
    private String email;

    @NotNull(message = "준공일 설정은 필수입니다.")
    private LocalDate completionDate;

    @NotNull(message = "동 수 설정은 필수입니다.")
    @Min(value = 1, message = "동 수는 1 이상이어야 합니다.")
    private Integer buildingCount;

    @NotNull(message = "최고 층 수 설정은 필수입니다.")
    @Min(value = 1, message = "최고 층 수는 1 이상이어야 합니다.")
    private Integer maxFloor;

    @NotNull(message = "주차 가능 대수 설정은 필수입니다.")
    @Min(value = 0, message = "주차 가능 대수는 0 이상이어야 합니다.")
    private Integer parkingCapacity;
}
