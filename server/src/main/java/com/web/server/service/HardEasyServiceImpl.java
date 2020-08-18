package com.web.server.service;

import com.web.server.dto.HardEasy;
import com.web.server.repo.HardEasyDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.sql.SQLException;

@Service
public class HardEasyServiceImpl implements HardEasyService{

    @Autowired
    HardEasyDao hardEasyDao;

//    @Override
//    public HardEasy checkChoice(HardEasy hardEasy) throws SQLException {
//        return hardEasyDao.checkChoice(hardEasy);
//    }

    @Override
    @Transactional
    public void createChoice(HardEasy hardEasy) throws SQLException {
        if (hardEasyDao.checkChoice(hardEasy) == null){
            hardEasyDao.firstChoice(hardEasy);
        }else{
            hardEasyDao.hardEasyChoice(hardEasy);
        }

    }

//    @Override
//    public void createHardChoice(HardEasy hardEasy) throws SQLException {
//        hardEasyDao.createHardChoice(hardEasy);;
//    }
//
//    @Override
//    public void deleteChoice(HardEasy hardEasy) throws SQLException {
//        hardEasyDao.deleteChoice(hardEasy);
//    }
//
//    @Override
//    public void hardToEasyChange(HardEasy hardEasy) throws SQLException{
//        hardEasyDao.hardToEasyChange(hardEasy);
//    }
//
//    @Override
//    public void easyToHardChange(HardEasy hardEasy) throws SQLException{
//        hardEasyDao.easyToHardChange(hardEasy);
//    }
//
//    @Override
//    public int updateHardChoice(int boardId) throws SQLException{
//        return hardEasyDao.updateHardChoice(boardId);
//    }
//
//    @Override
//    public int updateEasyChoice(int boardId) throws SQLException{
//        return hardEasyDao.updateEasyChoice(boardId);
//    }
}
