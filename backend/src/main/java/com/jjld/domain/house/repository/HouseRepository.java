package com.jjld.domain.house.repository;

import com.jjld.domain.house.entity.Account;
import com.jjld.domain.house.entity.House;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface HouseRepository extends JpaRepository<House, Long>, JpaSpecificationExecutor<House> {
    House findByHouseId(Long houseId);

    House findByHouseIdAndHouseholderEmail(Long houseId, String householderEmail);

    // 세대 조회
    House findByHouseDongAndHouseHo(Integer houseDong, Integer houseHo);
}
