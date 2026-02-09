package com.jjld.domain.complaint.service;

import com.jjld.domain.complaint.dto.admin.ComplaintAdminAnswerResponse;
import com.jjld.domain.complaint.dto.admin.ComplaintAdminDetailResponse;
import com.jjld.domain.complaint.dto.admin.ComplaintAdminResponse;
import com.jjld.domain.complaint.dto.admin.ComplaintSearchCond;
import com.jjld.domain.complaint.dto.user.ComplaintReference;
import com.jjld.domain.complaint.dto.user.ComplaintUserDetailResponse;
import com.jjld.domain.complaint.dto.user.ComplaintUserResponse;
import com.jjld.domain.complaint.dto.user.ComplaintUserWrite;
import com.jjld.domain.complaint.entity.Complaint;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ComplaintAdminService {

    // 관리자의 민원 페이징 조회
    Page<ComplaintAdminResponse> search(ComplaintSearchCond cond, int page, int size);

    // 관리자의 민원 상세 조회
    ComplaintAdminDetailResponse findByComplaintId(Long complaintId);

    // 관리자의 민원 답변 작성
    void answerWrite(Long complaintId, Long adminId, ComplaintAdminAnswerResponse answerResponse);

    // 관리자가 조회할 민원 요약 저장
    void runSummaryBatch();
}
