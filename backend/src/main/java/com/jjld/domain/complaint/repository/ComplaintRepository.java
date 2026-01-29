package com.jjld.domain.complaint.repository;

import com.jjld.domain.complaint.entity.Complaint;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ComplaintRepository extends JpaRepository<Complaint, Long> {

    // 관리자 민원 상세 조회
    Complaint findByComplaintId(Long complaintId);

    // 로그인한 유저 기준의 민원 목록 조회
    List<Complaint> findByHouse_HouseId(Long houseId);
}
