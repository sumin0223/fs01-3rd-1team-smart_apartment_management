package com.jjld.domain.complaint.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ComplaintReference {
    private Long complaintId;
    private String title;
    private String category;
    private LocalDateTime createAt;

}
