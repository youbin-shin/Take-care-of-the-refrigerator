package com.web.server.service;

import com.web.server.dto.HardEasy;

import java.sql.SQLException;

public interface HardEasyService {

    void createHardChoice(HardEasy hardEasy) throws SQLException;

    void createEasyChoice(HardEasy hardEasy) throws SQLException;

    void deleteChoice(HardEasy hardEasy) throws SQLException;
}
