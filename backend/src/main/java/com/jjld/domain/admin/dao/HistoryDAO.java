package com.jjld.domain.admin.dao;

import com.jjld.domain.admin.entity.History;

public interface HistoryDAO {
    void createLog(History history);
}
