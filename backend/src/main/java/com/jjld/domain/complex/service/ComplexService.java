package com.jjld.domain.complex.service;

import com.jjld.domain.complex.dto.ComplexReq;
import jakarta.validation.Valid;

public interface ComplexService {
    void createComplex(Long adminId, ComplexReq complexReq);
}
