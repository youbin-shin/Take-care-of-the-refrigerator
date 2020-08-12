package com.web.server.service;

import com.web.server.dto.HardEasy;
import com.web.server.repo.HardEasyDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class HardEasyServiceImpl implements HardEasyService{

    @Autowired
    HardEasyDao hardEasyDao;

    @Override
    public HardEasy checkChoice(HardEasy hardEasy) throws SQLException {
        return hardEasyDao.checkChoice(hardEasy);
    }

    @Override
    public void createEasyChoice(HardEasy hardEasy) throws SQLException {
        hardEasyDao.createEasyChoice(hardEasy);
    }

    @Override
    public void createHardChoice(HardEasy hardEasy) throws SQLException {
        hardEasyDao.createHardChoice(hardEasy);;
    }

    @Override
    public void deleteChoice(HardEasy hardEasy) throws SQLException {
        hardEasyDao.deleteChoice(hardEasy);
    }

    @Override
    public void hardToEasyChange(HardEasy hardEasy) throws SQLException{
        hardEasyDao.hardToEasyChange(hardEasy);
    }

    @Override
    public void easyToHardChange(HardEasy hardEasy) throws SQLException{
        hardEasyDao.easyToHardChange(hardEasy);
    }

    @Override
    public int updateHardChoice(HardEasy hardEasy) throws SQLException{
        return hardEasyDao.updateHardChoice(hardEasy);
    }

    @Override
    public int updateEasyChoice(HardEasy hardEasy) throws SQLException{
        return hardEasyDao.updateEasyChoice(hardEasy);
    }
}
