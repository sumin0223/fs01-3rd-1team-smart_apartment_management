package com.jjld.domain.complex.service;

import com.jjld.domain.admin.dao.AdminDAO;
import com.jjld.domain.admin.entity.Admin;
import com.jjld.domain.admin.entity.Enum.AdminRole;
import com.jjld.domain.complex.dao.ComplexDAO;
import com.jjld.domain.complex.dto.ComplexReq;
import com.jjld.domain.complex.dto.ComplexRes;
import com.jjld.domain.complex.entity.Complex;
import com.jjld.global.exception.admin.AdminNotFoundException;
import com.jjld.global.exception.admin.SuperAdminOnlyException;
import com.jjld.global.exception.complex.ApartmentComplexAlreadyExistsException;
import com.jjld.global.exception.complex.ApartmentComplexNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ComplexServiceImpl implements ComplexService {
    private final ComplexDAO complexDAO;
    private final AdminDAO adminDAO;
    private final ModelMapper modelMapper;

    // 단지 정보 생성
    @Override
    public void createComplex(Long adminId, ComplexReq complexReq) {
        if (complexDAO.existsComplex()) {
            throw new ApartmentComplexAlreadyExistsException();
        }

        Admin admin = adminDAO.getAdmin(adminId)
                .orElseThrow(() -> new AdminNotFoundException());

        if (admin.getAdminRole().equals(AdminRole.ADMIN)) {
            throw new SuperAdminOnlyException();
        }

        Complex complex = modelMapper.map(complexReq, Complex.class);

        complexDAO.createComplex(complex);
    }

    @Override
    public ComplexRes getComplex() {
        Complex complex = complexDAO.getComplex().orElse(null);

        if (complex == null) {
            throw new ApartmentComplexNotFoundException();
        }

        ComplexRes response = modelMapper.map(complex, ComplexRes.class);

        return response;
    }
}
