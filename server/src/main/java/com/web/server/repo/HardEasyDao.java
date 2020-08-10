package com.web.server.repo;

import com.web.server.dto.HardEasy;

import java.sql.SQLException;

public interface HardEasyDao {

    HardEasy createHardChoice(HardEasy hardEasy) throws SQLException;

    void createEasyChoice(HardEasy hardEasy) throws SQLException;

    void deleteChoice(HardEasy hardEasy) throws SQLException;

    int updateHardChoice(Integer boardId) throws SQLException;

    int updateEasyChoice(Integer boardId) throws SQLException;

    int checkExistTable(Integer boardId, Integer userId) throws  SQLException;
}
