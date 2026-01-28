package com.jjld.domain.admin.service;

import com.jjld.domain.admin.dao.AdminDAO;
import com.jjld.domain.admin.dto.AdminReq;
import com.jjld.domain.admin.dto.AdminRes;
import com.jjld.domain.admin.dto.AdminSearchCondition;
import com.jjld.domain.admin.entity.Admin;
import com.jjld.domain.admin.specification.AdminSpecification;
import com.jjld.global.exception.admin.AdminNotFoundException;
import com.jjld.global.exception.admin.DuplicateAdminLoginIdException;
import com.jjld.global.exception.admin.PasswordMismatchException;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;
import java.util.List;

import static com.jjld.global.exception.ErrorCode.PASSWORD_MISMATCH;

@Service
@RequiredArgsConstructor
@Builder
public class AdminServiceImpl implements AdminService {
    private final AdminDAO adminDAO;
    private final ModelMapper modelMapper;

    // adminId를 이용해 관리자 조회
    @Override
    public AdminRes getAdmin(Long adminId) {
        Admin admin = adminDAO.getAdmin(adminId)
                .orElseThrow(() -> new AdminNotFoundException());

        AdminRes response = modelMapper.map(admin, AdminRes.class);

        return response;
    }

    // 관리자 추가
    @Override
    public void createAdmin(AdminReq adminReq) {
        if (adminDAO.findByAdminLoginId(adminReq.getAdminLoginId()).isPresent()) {
            throw new DuplicateAdminLoginIdException();
        }

        if (!adminReq.getAdminPass().equals(adminReq.getConfirmPass())) {
            throw new PasswordMismatchException();
        }

        Admin admin = Admin.builder()
                .adminLoginId(adminReq.getAdminLoginId())
                .adminPass(adminReq.getAdminPass())
                .state(false)
                .adminRole(adminReq.getAdminRole())
                .build();

        adminDAO.createAdmin(admin);
    }

    // adminId를 이용해 관리자 삭제
    @Override
    public void deleteAdmin(Long adminId) {
        if (adminDAO.getAdmin(adminId).isEmpty()) {
            throw new AdminNotFoundException();
        }

        adminDAO.deleteAdmin(adminId);
    }

    // 관리자 목록을 조회
    @Override
    public List<AdminRes> getAdmins() {
        List<Admin> admins = adminDAO.getAdmins();
        List<AdminRes> response = modelMapper.map(admins, List.class);
        return response;
    }

    // 관리자 목록 필터 조회
    @Override
    public Page<AdminRes> getAdmins(AdminSearchCondition cond, Pageable pageable) {
        Specification<Admin> spec = AdminSpecification.withCondition(cond);
        Page<Admin> admins = adminDAO.getAdmins(spec, pageable);
        Page<AdminRes> response = admins.map(admin -> modelMapper.map(admin, AdminRes.class));
        return response;
    }
}
