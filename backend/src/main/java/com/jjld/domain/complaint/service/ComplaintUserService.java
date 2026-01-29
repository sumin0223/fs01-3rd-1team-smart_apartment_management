package com.jjld.domain.complaint.service;

import com.jjld.domain.complaint.dto.user.ComplaintReference;
import com.jjld.domain.complaint.dto.user.ComplaintUserDetailResponse;
import com.jjld.domain.complaint.dto.user.ComplaintUserResponse;
import com.jjld.domain.complaint.dto.user.ComplaintUserWrite;

import java.util.List;

public interface ComplaintUserService {

    // 로그인한 입주민 기준의 민원 목록 조회
    List<ComplaintUserResponse> findByHouse_HouseId(Long houseId);

    // 로그인한 입주민 기준의 민원 상세 조회
    ComplaintUserDetailResponse findByComplaintIdAndHouse_HouseId(Long houseId, Long complaintId);

    // 입주민 민원 작성 시 참조할 민원 목록 조회
    List<ComplaintReference> getReferenceComplaints(Long houseId);

    // 입주민 민원 작성
    Long write(Long houseId, ComplaintUserWrite userWrite);
}
