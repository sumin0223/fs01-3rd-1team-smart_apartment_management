package com.jjld.domain.complaint.dto.admin;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ComplaintAdminDetailResponse {
    private Long complaintId;
    private String title;
    private String category;
    private Integer houseDong;
    private Integer houseHo;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;

    private String content;
    private String summary;
    private String answer;
    private String adminName;
}
