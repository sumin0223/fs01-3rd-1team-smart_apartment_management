package com.jjld.domain.complaint.dto.user;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ComplaintUserDetailResponse {
    private Long complaintId;
    private String category;
    private String title;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    private String content;
    private String answer;

    private boolean canEdit;
    private boolean canDelete;

}
