package com.jjld.domain.house.repository;

import com.jjld.domain.house.entity.House;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HouseRepository extends JpaRepository<House, Long> {
}
