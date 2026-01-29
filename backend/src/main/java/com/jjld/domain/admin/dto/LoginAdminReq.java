package com.jjld.domain.admin.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginAdminReq {
    @NotBlank(message = "아이디를 입력해주세요.")
    private String adminLoginId;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String adminPass;
}
