package com.web.server.service;

import com.web.server.dto.HardEasy;
import org.apache.ibatis.session.SqlSessionException;

import java.sql.SQLException;

public interface HardEasyService {

    HardEasy checkChoice(HardEasy hardEasy) throws SQLException;

    void createChoice(HardEasy hardEasy) throws SQLException;
}
