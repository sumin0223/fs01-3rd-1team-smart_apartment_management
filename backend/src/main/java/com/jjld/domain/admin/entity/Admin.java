package com.jjld.domain.admin.entity;

import com.jjld.domain.admin.entity.Enum.AdminRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "admin")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long adminId;  // 자동 증가 PK

    @Column(nullable = false, unique = true, length = 50)
    private String adminLoginId;  // 로그인 아이디

    @Column(nullable = false)
    private String adminPass;  // 비밀번호

    @Column(length = 50)
    private String adminName;  // 이름

    @Column(length = 20)
    private String adminPhone;  // 전화번호

    @Column(length = 100)
    private String adminEmail;  // 이메일

    @Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT TRUE")
    private Boolean isFirstLogin = true;  // 최초 로그인 여부 (true=최초 로그인, false=최초 로그인 완료 후)

    @Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
    private Boolean state = false;  // 로그인 상태 (true=로그인, false=로그아웃)

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AdminRole adminRole;  // 관리자 권한

    @CreationTimestamp
    @Column(columnDefinition = "DATETIME")
    private LocalDateTime createdAt;  // 생성일
}
