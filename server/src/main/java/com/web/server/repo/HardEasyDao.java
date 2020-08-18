package com.web.server.repo;

import com.web.server.dto.HardEasy;

import java.sql.SQLException;

public interface HardEasyDao {

    HardEasy checkChoice(HardEasy hardEasy) throws SQLException;

    void firstChoice(HardEasy hardEasy) throws SQLException;

    void hardEasyChoice(HardEasy hardEasy) throws SQLException;
//
//    void createHardChoice(HardEasy hardEasy) throws SQLException;
//
//    void deleteChoice(HardEasy hardEasy) throws SQLException;
//
//    int updateHardChoice(int boardId) throws SQLException;
//
//    int updateEasyChoice(int boardId) throws SQLException;
//
//    void hardToEasyChange (HardEasy hardEasy) throws SQLException;
//
//    void easyToHardChange (HardEasy hardEasy) throws SQLException;
}
