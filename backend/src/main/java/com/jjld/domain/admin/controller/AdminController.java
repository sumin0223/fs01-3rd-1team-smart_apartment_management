package com.jjld.domain.admin.controller;

import com.jjld.domain.admin.dto.AdminReq;
import com.jjld.domain.admin.dto.AdminRes;
import com.jjld.domain.admin.dto.AdminSearchCondition;
import com.jjld.domain.admin.service.AdminService;
import com.jjld.global.response.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Pageable;
import java.util.List;

@RestController
@RequestMapping("/admin/api")
@RequiredArgsConstructor
public class AdminController {
    private final AdminService adminService;

    // adminId를 이용해 관리자 조회
    @GetMapping("/{adminId}")
    public ResponseEntity<?> getAdmin(@PathVariable("adminId") Long adminId) {
        AdminRes response = adminService.getAdmin(adminId);
        return ResponseEntity.ok(ApiResponse.success(response));
    }

    // 관리자 페이지에서 관리자 추가
    @PostMapping
    public ResponseEntity<?> createAdmin(@Valid @RequestBody AdminReq adminReq) {
        adminService.createAdmin(adminReq);
        return ResponseEntity.ok(ApiResponse.success("관리자 생성을 성공했습니다."));
    }

    // 관리자 페이지에서 adminId를 이용해 관리자 삭제
    @DeleteMapping("/{adminId}")
    public ResponseEntity<?> deleteAdmin(@PathVariable("adminId") Long adminId) {
        adminService.deleteAdmin(adminId);
        return ResponseEntity.ok(ApiResponse.success("관리자 삭제를 성공했습니다."));
    }

    // 관리자 페이지에서 관리자 목록을 조회
    @GetMapping
    public ResponseEntity<?> getAdmins() {
        List<AdminRes> response = adminService.getAdmins();
        return ResponseEntity.ok(ApiResponse.success(response));
    }

    // 관리자 페이지에서 관리자 목록 필터 조회
    @GetMapping("/filter")
    public ResponseEntity<?> getAdmins(AdminSearchCondition cond, Pageable pageable) {
        Page<AdminRes> response = adminService.getAdmins(cond, pageable);
        return ResponseEntity.ok(ApiResponse.success(response));
    }

    //

    // 관리자 정보 수정
    @PutMapping("/{adminId}/authority")
    public ResponseEntity<?> updateAdminAuthority(
            @PathVariable("adminId") Long adminId,
            @Valid @RequestBody AdminReq adminReq) {
        return null;
    }
}
