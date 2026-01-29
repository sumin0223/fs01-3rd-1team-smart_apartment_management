package com.jjld.domain.complaint.repository;

import com.jjld.domain.complaint.entity.Complaint;
import com.jjld.domain.house.entity.House;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ComplaintRepository extends JpaRepository<Complaint, Long> {

    // 관리자 민원 상세 조회
    Complaint findByComplaintId(Long complaintId);

    // 로그인한 입주민 기준의 민원 목록 조회
    List<Complaint> findByHouse_HouseId(Long houseId);

    // 로그인한 입주민 기준의 민원 상세 조회
    Complaint findByComplaintIdAndHouse_HouseId(Long houseId, Long complaintId);

    // 입주민의 민원 작성 시 참조할 민원 목록 조회
    List<Complaint> findByHouse_HouseIdOrderByCreatedAtDesc(Long houseId);

}
