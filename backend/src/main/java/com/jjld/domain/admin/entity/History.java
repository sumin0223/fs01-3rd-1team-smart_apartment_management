package com.jjld.domain.admin.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name="admin_log_history")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class History {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long historyId;  // PK

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "admin_id", nullable = false)
    private Admin admin;  // 관리자와 다대일 관계 (한 관리자가 여러 번 로그인 가능)

    @Column(nullable = false, length = 45)
    private String ipAddress;  // IPv4 + IPv6 고려해서 45 추천

    @Column(nullable = false)
    private Boolean success;  // 로그인 성공 여부

    @Column
    private String message;  // 실패 시 이유

    @CreationTimestamp
    @Column(columnDefinition = "DATETIME")
    private LocalDateTime createdAt;  // 로그인 시각
}
