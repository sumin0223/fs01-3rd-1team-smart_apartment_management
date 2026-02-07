package com.jjld.domain.entrancedoor.repository;

import com.jjld.domain.entrancedoor.entity.EntranceOpenRequest;
import com.jjld.domain.entrancedoor.entity.Enum.DoorCallStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EntranceOpenRequestRepository extends JpaRepository<EntranceOpenRequest, Long> {

    //처리 안 된 호출만
    List<EntranceOpenRequest> findByStatus(DoorCallStatus status);
}
