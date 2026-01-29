package com.jjld.domain.admin.repository;

import com.jjld.domain.admin.entity.History;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoryRepository extends JpaRepository<History,Integer> {
}
