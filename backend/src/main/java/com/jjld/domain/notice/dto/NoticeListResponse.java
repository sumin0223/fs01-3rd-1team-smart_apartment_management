package com.jjld.domain.notice.dto;

import com.jjld.domain.notice.entity.Notice;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NoticeListResponse {
    private Long noticeId;
    private String adminName;
    private String noticeTitle;
    private LocalDateTime createdAt;

}
