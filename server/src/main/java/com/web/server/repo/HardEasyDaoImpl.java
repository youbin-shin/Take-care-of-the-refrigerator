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
    public void createEasyChoice(HardEasy hardEasy) throws SQLException {
        template.selectOne(ns+"easyChoice",hardEasy);
    }

    @Override
    public void createHardChoice(HardEasy hardEasy) throws SQLException {
        template.selectOne(ns+"hardChoice",hardEasy);
    }

    @Override
    public void deleteChoice(HardEasy hardEasy) throws SQLException{
        template.delete(ns+"deleteChoice",hardEasy);
    }

    @Override
    public int updateHardChoice(HardEasy hardEasy) throws SQLException{
        return template.selectOne(ns+"updateHardChoice",hardEasy);
    }

    @Override
    public int updateEasyChoice(HardEasy hardEasy) throws SQLException{
        return template.selectOne(ns+"updateEasyChoice",hardEasy);
    }

    @Override
    public void hardToEasyChange(HardEasy hardEasy) throws SQLException{
        template.update(ns+"hardToEasyChangeChoice",hardEasy);
    }

    @Override
    public void easyToHardChange(HardEasy hardEasy) throws SQLException{
        template.update(ns+"easyToHardChangeChoice",hardEasy);
    }
}
