package com.jjld.domain.complaint.dao;

import com.jjld.domain.complaint.dto.user.ComplaintUserWrite;
import com.jjld.domain.complaint.entity.Complaint;

public interface ComplaintDAO {

    // 관리자의 민원 상세 조회
    Complaint findByComplaintId(Long complaintId);

    // 입주민의 민원 상세 조회
    Complaint findByHouseIdComplaintId(Long houseId, Long complaintId);

    // 입주민 민원 작성
    Long save(Complaint complaint);

}
