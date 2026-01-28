package com.jjld.domain.admin.service;

import com.jjld.domain.admin.dto.AdminReq;
import com.jjld.domain.admin.dto.AdminRes;
import com.jjld.domain.admin.dto.AdminSearchCondition;

import com.jjld.domain.admin.entity.Enum.AdminRole;
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
}
