package com.jjld.domain.admin.service;

import com.jjld.domain.admin.dto.*;

import com.jjld.domain.admin.entity.Enum.AdminRole;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface AdminService {
    AdminRes getAdmin(Long adminId);

    void createAdmin(AdminReq adminReq);

    void deleteAdmin(Long adminId);

    List<AdminRes> getAdmins();

    Page<AdminRes> getAdmins(AdminSearchCondition cond, Pageable pageable);

    void updateAdminAuthority(Long adminId, Long targetAdminId, AdminRole adminRole);

    void updateAdmin(Long adminId, UpdateAdminReq updateAdminReq);

    LoginAdminRes loginAdmin(LoginAdminReq loginAdminReq, HttpServletRequest servletRequest);
}
