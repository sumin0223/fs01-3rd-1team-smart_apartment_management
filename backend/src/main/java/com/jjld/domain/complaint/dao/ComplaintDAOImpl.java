package com.jjld.domain.complaint.dao;


import com.jjld.domain.complaint.entity.Complaint;
import com.jjld.domain.complaint.repository.ComplaintRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ComplaintDAOImpl implements ComplaintDAO{
    private final ComplaintRepository complaintRepository;

    // 관리자의 민원 조회
    @Override
    public List<Complaint> complaintList() {
        return complaintRepository.findAll();
    }

    // 관리자의 민원 상세 조회
    @Override
    public Complaint findByComplaintId(Long complaintId) {
        return complaintRepository.findByComplaintId(complaintId);
    }
}
