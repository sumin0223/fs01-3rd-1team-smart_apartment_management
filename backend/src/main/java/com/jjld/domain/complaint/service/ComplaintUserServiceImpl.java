package com.jjld.domain.complaint.service;

import com.jjld.domain.complaint.dao.ComplaintDAO;
import com.jjld.domain.complaint.dto.user.ComplaintReference;
import com.jjld.domain.complaint.dto.user.ComplaintUserDetailResponse;
import com.jjld.domain.complaint.dto.user.ComplaintUserResponse;
import com.jjld.domain.complaint.dto.user.ComplaintUserWrite;
import com.jjld.domain.complaint.entity.Complaint;
import com.jjld.domain.complaint.entity.Enum.ComplaintStatus;
import com.jjld.domain.complaint.repository.ComplaintRepository;
import com.jjld.global.exception.complaint.ComplaintNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ComplaintUserServiceImpl implements ComplaintUserService{
    private final ComplaintRepository complaintRepository;
    private final ComplaintDAO complaintDAO;
    private final ModelMapper modelMapper;


    // 세대별 작성한 민원 목록 조회
    @Override
    public List<ComplaintUserResponse> findByHouse_HouseId(Long houseId) {
        List<Complaint> userComplaint = complaintRepository.findByHouse_HouseId(houseId);
        if(userComplaint.isEmpty()){
            throw new ComplaintNotFoundException("작성한 민원이 없습니다");
        }

        return userComplaint.stream()
                .map(complaint -> ComplaintUserResponse.builder()
                        .complaintId(complaint.getComplaintId())
                        .title(complaint.getTitle())
                        .category(String.valueOf(complaint.getCategory()))
                        .status(String.valueOf(complaint.getStatus()))
                        .createAt(complaint.getCreatedAt())
                        .build())
                .collect(Collectors.toList());
    }

    // 자신이 작성한 민원 상세 조회
    @Override
    public ComplaintUserDetailResponse findByComplaintIdAndHouse_HouseId(Long houseId, Long complaintId) {
        Complaint complaint = complaintDAO.findByHouseIdComplaintId(houseId, complaintId);
        if (complaint == null){
            throw new ComplaintNotFoundException("상세 조회하려는 민원글이 없습니다");
        }

        String answer = Optional.ofNullable(complaint.getComplaintReply())
                .map(r -> r.getAnswer())
                .orElse(null);

        ComplaintUserDetailResponse userDetailResponse = ComplaintUserDetailResponse.builder()
                .complaintId(complaint.getComplaintId())
                .category(complaint.getCategory().name())
                .title(complaint.getTitle())
                .createAt(complaint.getCreatedAt())
                .updateAt(complaint.getUpdatedAt())
                .content(complaint.getContent())
                .answer(answer)
                .canEdit(complaint.getStatus() == ComplaintStatus.WAITING)
                .canDelete(complaint.getStatus() == ComplaintStatus.WAITING)
                .build();

        return userDetailResponse;
    }

    // 민원 작성 시 참조할 민원 목록
    @Override
    public List<ComplaintReference> getReferenceComplaints(Long houseId) {
        List<Complaint> reference = complaintRepository.findByHouse_HouseIdOrderByCreatedAtDesc(houseId);
        if (reference.isEmpty()) {
            throw new ComplaintNotFoundException("참조할 민원 내역이 없습니다");
        }
        return reference.stream()
                .map(r -> new ComplaintReference(
                        r.getComplaintId(),
                        r.getTitle(),
                        r.getCategory().name(),
                        r.getCreatedAt()
                )).toList();
    }

    @Override
    public Long write(Long houseId, ComplaintUserWrite userWrite) {
        ComplaintUserWrite complaintUserWrite = ComplaintUserWrite.builder(

                )
                .build();

        return null;
    }
}
