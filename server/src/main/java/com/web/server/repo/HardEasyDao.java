package com.web.server.repo;

import com.web.server.dto.HardEasy;

import java.sql.SQLException;

public interface HardEasyDao {

    HardEasy checkChoice(HardEasy hardEasy) throws SQLException;

    void createEasyChoice(HardEasy hardEasy) throws SQLException;

    void createHardChoice(HardEasy hardEasy) throws SQLException;

    void deleteChoice(HardEasy hardEasy) throws SQLException;

    int updateHardChoice(HardEasy hardEasy) throws SQLException;

    int updateEasyChoice(HardEasy hardEasy) throws SQLException;

    void hardToEasyChange (HardEasy hardEasy) throws SQLException;

    void easyToHardChange (HardEasy hardEasy) throws SQLException;
}
