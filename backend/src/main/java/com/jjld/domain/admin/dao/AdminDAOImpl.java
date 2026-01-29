package com.jjld.domain.admin.dao;

import com.jjld.domain.admin.entity.Admin;
import com.jjld.domain.admin.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class AdminDAOImpl implements AdminDAO {
    private final AdminRepository adminRepository;

    // adminId를 이용해 관리자 조회
    @Override
    public Optional<Admin> getAdmin(Long adminId) {
        return adminRepository.findById(adminId);
    }

    // 관리자 추가
    @Override
    public void createAdmin(Admin admin) {
        adminRepository.save(admin);
    }

    // adminLoginId를 이용해 관리자 조회
    @Override
    public Optional<Admin> findByAdminLoginId(String adminLoginId) {
        return adminRepository.findByAdminLoginId(adminLoginId);
    }

    // adminId를 이용해 관리자 삭제
    @Override
    public void deleteAdmin(Long adminId) {
        adminRepository.deleteById(adminId);
    }

    // 관리자 목록을 조회
    @Override
    public List<Admin> getAdmins() {
        List<Admin> admins = adminRepository.findAll();
        return admins;
    }

    // 관리자 목록 필터 조회
    @Override
    public Page<Admin> getAdmins(Specification<Admin> spec, Pageable pageable) {
        return adminRepository.findAll(spec, pageable);
    }

    // 관리자 권한 수정
    @Override
    public void updateAdminAuthority(Admin targetAdmin) {
        adminRepository.save(targetAdmin);
    }

    // 관리자 정보 수정
    @Override
    public void updateAdmin(Admin admin) {
        adminRepository.save(admin);
    }
}
