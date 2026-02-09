package com.jjld.domain.complaint.repository;

import com.jjld.domain.complaint.entity.Complaint;
import com.jjld.domain.complaint.entity.ComplaintAnalysis;
import com.jjld.domain.complaint.entity.Enum.SummaryStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public interface ComplaintRepository extends JpaRepository<Complaint, Long>, JpaSpecificationExecutor<Complaint> {

    // 관리자 민원 상세 조회
    Complaint findByComplaintId(Long complaintId);

    // 로그인한 입주민 기준의 민원 목록 조회
    List<Complaint> findByHouse_HouseIdAndHouseholderEmail(Long houseId, String email);

    // 로그인한 입주민 기준의 민원 상세 조회
    Complaint findByComplaintIdAndHouse_HouseIdAndHouseholderEmail(Long complaintId, Long houseId,  String email);

    // 입주민 민원 작성 시 참조할 민원 목록 조회
    List<Complaint> findByHouse_HouseIdOrderByCreatedAtDesc(Long houseId);

    // 입주민 민원 작성
    Optional<Complaint> findByHouse_HouseIdAndComplaintId(Long houseId, Long complaintId);

    // 입주민 민원 삭제
    // 삭제 대상 complaint를 참조하는 모든 complaint를 조회
    List<Complaint> findAllByReferenceComplaintsContainsAndHouse_HouseIdAndHouseholderEmail(Complaint complaint, Long houseId, String email);
    void deleteByComplaintIdAndHouse_HouseIdAndHouseholderEmail(Long complaint, Long houseId, String email);

    // 요약 대기 상태 민원만 조회
    List<Complaint> findBySummaryStatus(SummaryStatus status);

}
