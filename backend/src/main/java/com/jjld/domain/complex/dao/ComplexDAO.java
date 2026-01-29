package com.jjld.domain.complex.dao;

import com.jjld.domain.complex.entity.Complex;

import java.util.Optional;

public interface ComplexDAO {
    Boolean existsComplex();

    void createComplex(Complex complex);

    Optional<Complex> getComplex();
}
