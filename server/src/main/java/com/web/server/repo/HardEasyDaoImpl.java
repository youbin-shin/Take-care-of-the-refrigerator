package com.web.server.repo;

import com.web.server.dto.HardEasy;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Repository
class HardEasyDaoImpl implements HardEasyDao{
    private String ns = "com.web.server.repo.HardEasyDao.";

    @Autowired
    SqlSessionTemplate template;


    @Override
    public HardEasy checkChoice(HardEasy hardEasy) throws  SQLException{
        return template.selectOne(ns+"checkChoice",hardEasy);
    }

    @Override
    public void firstChoice(HardEasy hardEasy) throws SQLException {
        template.selectOne(ns+"firstChoice",hardEasy);
    }

    @Override
    public void hardEasyChoice(HardEasy hardEasy) throws SQLException {
        template.selectOne(ns+"hardEasyChoice",hardEasy);
    }
//
//    @Override
//    public void deleteChoice(HardEasy hardEasy) throws SQLException{
//        template.delete(ns+"deleteChoice",hardEasy);
//    }
//
//    @Override
//    public int updateHardChoice(int boardId) throws SQLException{
//        return template.selectOne(ns+"updateHardChoice",boardId);
//    }
//
//    @Override
//    public int updateEasyChoice(int boardId) throws SQLException{
//        return template.selectOne(ns+"updateEasyChoice",boardId);
//    }
//
//    @Override
//    public void hardToEasyChange(HardEasy hardEasy) throws SQLException{
//        template.update(ns+"hardToEasyChangeChoice",hardEasy);
//    }
//
//    @Override
//    public void easyToHardChange(HardEasy hardEasy) throws SQLException{
//        template.update(ns+"easyToHardChangeChoice",hardEasy);
//    }
}
