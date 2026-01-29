package com.jjld.domain.admin.dao;

import com.jjld.domain.admin.entity.History;
import com.jjld.domain.admin.repository.HistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class HistoryDAOImpl implements HistoryDAO {
    private final HistoryRepository historyRepository;

    @Override
    public void createLog(History history) {
        historyRepository.save(history);
    }
}
