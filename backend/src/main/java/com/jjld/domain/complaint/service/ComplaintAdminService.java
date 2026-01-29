package com.jjld.domain.complaint.service;

import com.jjld.domain.complaint.dto.admin.ComplaintAdminDetailResponse;
import com.jjld.domain.complaint.dto.admin.ComplaintAdminResponse;
import com.jjld.domain.complaint.dto.user.ComplaintReference;
import com.jjld.domain.complaint.dto.user.ComplaintUserDetailResponse;
import com.jjld.domain.complaint.dto.user.ComplaintUserResponse;
import com.jjld.domain.complaint.dto.user.ComplaintUserWrite;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ComplaintAdminService {

    // 관리자의 민원 페이징 조회
    Page<ComplaintAdminResponse> findAll(int page, int size);

    // 관리자의 민원 상세 조회
    ComplaintAdminDetailResponse findByComplaintId(Long complaintId);

}
