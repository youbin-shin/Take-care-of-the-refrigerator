package com.web.server.service;

import com.web.server.dto.HardEasy;
import org.apache.ibatis.session.SqlSessionException;

import java.sql.SQLException;

public interface HardEasyService {

//    HardEasy checkChoice(HardEasy hardEasy) throws SQLException;

    void createChoice(HardEasy hardEasy) throws SQLException;

//    void createEasyChoice(HardEasy hardEasy) throws SQLException;
//
//    void deleteChoice(HardEasy hardEasy) throws SQLException;
//
//    void hardToEasyChange(HardEasy hardEasy) throws  SQLException;
//
//    void easyToHardChange(HardEasy hardEasy) throws  SQLException;
//
//    int updateHardChoice(int boardId) throws SQLException;
//
//    int updateEasyChoice(int boardId) throws SQLException;
}
