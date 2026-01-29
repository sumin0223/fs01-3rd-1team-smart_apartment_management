package com.jjld.domain.complaint.controller;

import com.jjld.domain.complaint.dto.user.ComplaintReference;
import com.jjld.domain.complaint.dto.user.ComplaintUserDetailResponse;
import com.jjld.domain.complaint.dto.user.ComplaintUserResponse;
import com.jjld.domain.complaint.dto.user.ComplaintUserWrite;
import com.jjld.domain.complaint.service.ComplaintAdminService;
import com.jjld.domain.complaint.service.ComplaintAdminServiceImpl;
import com.jjld.domain.complaint.service.ComplaintUserService;
import com.jjld.global.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/api")
@RequiredArgsConstructor
public class ComplaintUserController {
    private final ComplaintUserService service;

    // 입주민 자신이 등록한 민원 목록 조회
    @GetMapping("/list")
    public ResponseEntity<?> getUserComplaintList(@RequestParam Long houseId){
        List<ComplaintUserResponse> userComplaint = service.findByHouse_HouseId(houseId);

        return ResponseEntity.ok(
                ApiResponse.success(userComplaint)
        );
    }

    // 입주민 자신이 등록한 민원 상세 조회
    @GetMapping("/complaints/{complaintId}")
    public ResponseEntity<ApiResponse<ComplaintUserDetailResponse>> getUserComplaintDetail(
            @PathVariable Long complaintId,
            @RequestParam Long houseId
    ){
        ComplaintUserDetailResponse response =
                service.findByComplaintIdAndHouse_HouseId(complaintId, houseId);

        return ResponseEntity.ok(
                ApiResponse.success(response)
        );
    }

    // 민원 작성 시 참조할 민원 목록 조회
    @GetMapping("/reference/{houseId}")
    public ResponseEntity<?> getReference(@RequestParam("houseId") Long houseId){
        List<ComplaintReference> reference = service.getReferenceComplaints(houseId);

        return ResponseEntity.ok(
                ApiResponse.success(reference)
        );
    }

    // 입주민 민원 작성
    @PostMapping("/write")
    public ResponseEntity<?> writeComplaint(
            @RequestParam Long houseId,
            @RequestBody ComplaintUserWrite userWrite){
        service.write(houseId, userWrite);
        return ResponseEntity.ok(
                ApiResponse.success(HttpStatus.OK)
        );
    }
}
