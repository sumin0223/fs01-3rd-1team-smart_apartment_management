package com.jjld.domain.complex.repository;

import com.jjld.domain.complex.entity.Complex;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ComplexRepository extends JpaRepository<Complex, Long> {
    Optional<Complex> findTopByOrderByIdAsc();
}
