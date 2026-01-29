package com.jjld.domain.complex.service;

import com.jjld.domain.complex.dto.ComplexReq;
import com.jjld.domain.complex.dto.ComplexRes;
import jakarta.validation.Valid;

public interface ComplexService {
    void createComplex(Long adminId, ComplexReq complexReq);

    ComplexRes getComplex();
}
