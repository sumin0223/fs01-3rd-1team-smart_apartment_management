package com.jjld.domain.complaint.service;

import com.jjld.domain.complaint.dao.ComplaintDAO;
import com.jjld.domain.complaint.dao.ComplaintDAOImpl;
import com.jjld.domain.complaint.dto.admin.ComplaintAdminDetailResponse;
import com.jjld.domain.complaint.dto.admin.ComplaintAdminResponse;
import com.jjld.domain.complaint.dto.user.ComplaintReference;
import com.jjld.domain.complaint.dto.user.ComplaintUserDetailResponse;
import com.jjld.domain.complaint.dto.user.ComplaintUserResponse;
import com.jjld.domain.complaint.dto.user.ComplaintUserWrite;
import com.jjld.domain.complaint.entity.Complaint;
import com.jjld.domain.complaint.entity.ComplaintAnalysis;
import com.jjld.domain.complaint.entity.Enum.ComplaintStatus;
import com.jjld.domain.complaint.repository.ComplaintRepository;
import com.jjld.global.exception.complaint.ComplaintNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ComplaintAdminServiceImpl implements ComplaintAdminService {

    private final ComplaintRepository complaintRepository;
    private final ComplaintDAO complaintDAO;

    // 관리자 민원 목록 페이징으로 조회
    public Page<ComplaintAdminResponse> findAll(int page, int size){
        Pageable pageable = PageRequest.of(page, size, Sort.by("complaintId").descending());

        Page<Complaint> complaintPage = complaintRepository.findAll(pageable);
        if(complaintPage == null){
            throw new ComplaintNotFoundException("해당 페이지의 민원이 없습니다");
        }

        return complaintPage.map(ComplaintAdminResponse::new);
    }


    // 관리자 민원 상세 조회
    @Override
    public ComplaintAdminDetailResponse findByComplaintId(Long complaintId) {
        Complaint complaint = complaintDAO.findByComplaintId(complaintId);
        if (complaint == null){
            throw new ComplaintNotFoundException();
        }

        // ai 요약이 없을 때 null
        String summary = Optional.ofNullable(complaint.getComplaintAnalysis())
                .map(ComplaintAnalysis::getSummary)
                .orElse(null);

        // 관리자 답변이 없을 때 null
        String answer = Optional.ofNullable(complaint.getComplaintReply())
                .map(r -> r.getAnswer())
                .orElse(null);

        // 관리자 답변이 없을 때 -> 답변 작성한 관리자 ID가 null
        String admin = Optional.ofNullable(complaint.getComplaintReply())
                .map(r -> r.getAdmin().getAdminName())
                .orElse(null);

        ComplaintAdminDetailResponse adminDetailResponse = ComplaintAdminDetailResponse.builder()
                .complaintId(complaint.getComplaintId())
                .houseDong(complaint.getHouse().getHouseDong())
                .houseHo(complaint.getHouse().getHouseHo())
                .category(complaint.getCategory().name())
                .createAt(complaint.getCreatedAt())
                .updateAt(complaint.getUpdatedAt())
                .title(complaint.getTitle())
                .content(complaint.getContent())
                .summary(summary)
                .answer(answer)
                .adminName(admin)
                .build();

        return adminDetailResponse;
    }

}
