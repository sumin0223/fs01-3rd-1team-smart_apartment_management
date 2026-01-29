package com.jjld.domain.complex.dao;

import com.jjld.domain.complex.entity.Complex;
import com.jjld.domain.complex.repository.ComplexRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ComplexDAOImpl implements ComplexDAO {
    private final ComplexRepository complexRepository;

    @Override
    public Boolean existsComplex() {
        return !complexRepository.findAll().isEmpty();
    }

    @Override
    public void createComplex(Complex complex) {
        complexRepository.save(complex);
    }
}
