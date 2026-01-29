package com.jjld.domain.complaint.dto;

import com.jjld.domain.complaint.entity.Complaint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ComplaintUserResponse {
    private Long complaintId;
    private String title;
    private String category;
    private String status;
    private LocalDateTime createAt;


}
