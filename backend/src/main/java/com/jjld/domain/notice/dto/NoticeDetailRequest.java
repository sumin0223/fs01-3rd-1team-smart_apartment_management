package com.jjld.domain.notice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NoticeDetailRequest {
    private Long noticeId;
    private Long adminId;
    private String noticeTitle;
    private String noticeContent;
}
