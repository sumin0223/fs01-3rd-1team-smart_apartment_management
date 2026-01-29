package com.jjld.domain.admin.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HistoryRes {
    private Long historyId;
    private Long adminId;
    private String ipAddress;
    private Boolean success;
    private String message;
    private LocalDateTime createdAt;
}
