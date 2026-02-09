package com.jjld.domain.complaint.repository;

import com.jjld.domain.complaint.entity.ComplaintAnalysis;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ComplaintAnalysisRepository extends JpaRepository<ComplaintAnalysis, Long> {

    // 민원 수정 시 기존 생성된 요약 삭제
    void deleteByComplaint_ComplaintId(Long complaintId);
}
