package com.jjld.domain.complaint.dao;


import com.jjld.domain.complaint.dto.user.ComplaintUserWrite;
import com.jjld.domain.complaint.entity.Complaint;
import com.jjld.domain.complaint.repository.ComplaintRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ComplaintDAOImpl implements ComplaintDAO{
    private final ComplaintRepository complaintRepository;

    // 관리자의 민원 상세 조회
    @Override
    public Complaint findByComplaintId(Long complaintId) {
        return complaintRepository.findByComplaintId(complaintId);
    }

    // 입주민 자신이 작성한 민원 상세 조회
    @Override
    public Complaint findByHouseIdComplaintId(Long houseId, Long complaintId) {
        return complaintRepository.findByComplaintIdAndHouse_HouseId(houseId, complaintId);
    }

    // 입주민 민원 작성
    @Override
    public Long save(Complaint complaint) {
        return complaintRepository.save(complaint).getComplaintId();
    }


}
