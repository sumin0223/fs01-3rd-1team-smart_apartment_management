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

    // 단지 정보 생성
    @Override
    public Boolean existsComplex() {
        return !complexRepository.findAll().isEmpty();
    }

    // 단지 정보 조회
    @Override
    public Optional<Complex> getComplex() {
        return complexRepository.findTopByOrderByIdAsc();
    }

    // 단지 정보 수정
    @Override
    public void updateComplex(Complex complex) {
        complexRepository.save(complex);
    }
}
