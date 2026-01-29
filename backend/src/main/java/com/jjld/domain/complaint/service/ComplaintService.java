package com.jjld.domain.complaint.service;

import com.jjld.domain.complaint.dto.ComplaintAdminDetailResponse;
import com.jjld.domain.complaint.dto.ComplaintAdminResponse;
import com.jjld.domain.complaint.dto.ComplaintUserResponse;
import com.jjld.domain.complaint.entity.Complaint;
import com.jjld.domain.complaint.repository.ComplaintRepository;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ComplaintService {

    // 관리자의 민원 페이징 조회
    Page<ComplaintAdminResponse> findAll(int page, int size);

    // 관리자의 민원 상세 조회
    ComplaintAdminDetailResponse findByComplaintId(Long complaintId);

    // 로그인한 유저 기준의 민원 목록 조회
    List<ComplaintUserResponse> findByHouse_HouseId(Long houseId);
}
