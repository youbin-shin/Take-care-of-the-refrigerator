package com.web.server.service;

import com.web.server.dto.HardEasy;
import com.web.server.repo.HardEasyDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class HardEasyServicelmpl implements HardEasyService{

    @Autowired
    HardEasyDao hardEasyDao;

    @Override
    public void createHardChoice(HardEasy hardEasy) throws SQLException {
        hardEasyDao.createHardChoice(hardEasy);
        hardEasyDao.updateHardChoice(hardEasy.getBoardId());
    }

    @Override
    public void createEasyChoice(HardEasy hardEasy) throws SQLException {
        hardEasyDao.createEasyChoice(hardEasy);
        hardEasyDao.updateEasyChoice(hardEasy.getBoardId());
    }

    @Override
    public void deleteChoice(HardEasy hardEasy) throws SQLException {
        hardEasyDao.deleteChoice(hardEasy);
        hardEasyDao.updateHardChoice(hardEasy.getBoardId());
        hardEasyDao.updateEasyChoice(hardEasy.getBoardId());
    }
}
