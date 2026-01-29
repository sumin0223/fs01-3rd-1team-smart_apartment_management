package com.jjld.domain.admin.dao;

import com.jjld.domain.admin.dto.AdminRes;
import com.jjld.domain.admin.dto.AdminSearchCondition;
import com.jjld.domain.admin.entity.Admin;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;

import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;

public interface AdminDAO {
    Optional<Admin> getAdmin(Long adminId);

    void createAdmin(Admin admin);

    Optional<Admin> findByAdminLoginId(String adminLoginId);

    void deleteAdmin(Long adminId);

    List<Admin> getAdmins();

    Page<Admin> getAdmins(Specification<Admin> spec, Pageable pageable);

    void updateAdminAuthority(Admin targetAdmin);

    void updateAdmin(Admin admin);
}
