package com.jjld.domain.complaint.dto;

import com.jjld.domain.complaint.entity.Complaint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ComplaintAdminResponse {
    private Long complaintId;
    private String title;
    private String category;
    private Integer houseDong;
    private Integer houseHo;
    private LocalDateTime createAt;
    private String status;

    public ComplaintAdminResponse(Complaint complaint) {
        this.complaintId = complaint.getComplaintId();
        this.title = complaint.getTitle();
        this.category = complaint.getCategory().name();
        this.houseDong = complaint.getHouse().getHouseDong();
        this.houseHo = complaint.getHouse().getHouseHo();
        this.createAt = complaint.getCreatedAt();
        this.status = complaint.getStatus().name();
    }
}
