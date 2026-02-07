package com.jjld.domain.entrancedoor.repository;

import com.jjld.domain.entrancedoor.dto.EntranceGateLogResponse;
import com.jjld.domain.entrancedoor.entity.EntranceGateLog;
import com.jjld.domain.house.entity.EntranceCard;
import com.jjld.domain.house.entity.House;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import javax.smartcardio.Card;
import java.util.List;
import java.util.Optional;

public interface EntranceGateLogRepository extends JpaRepository<EntranceGateLog, Long>, JpaSpecificationExecutor<EntranceGateLog> {
    // 동의 가장 최근 성공 출입
    Optional<EntranceGateLog> findTopByHouse_HouseDongAndOutcomeTrueOrderByAccessedAtDesc(Integer dong);


}
