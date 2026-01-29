package com.jjld.domain.complaint.dao;

import com.jjld.domain.complaint.entity.Complaint;

import java.util.List;

public interface ComplaintDAO {

    // 관리자의 민원 전체 목록 조회
    List<Complaint> complaintList();

    // 관리자의 민원 상세 조회
    Complaint findByComplaintId(Long complaintId);
}
