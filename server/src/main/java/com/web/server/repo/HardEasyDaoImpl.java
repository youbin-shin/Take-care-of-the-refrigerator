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
    public HardEasy createHardChoice(HardEasy hardEasy) throws  SQLException{
        return template.selectOne(ns+"hardChoice",hardEasy);
    }

    @Override
    public void createEasyChoice(HardEasy hardEasy) throws SQLException {
        template.selectOne(ns+"EardChoice",hardEasy);
    }

    @Override
    public void deleteChoice(HardEasy hardEasy) throws SQLException{
        template.delete(ns+"deleteChoice",hardEasy);
    }

    @Override
    public int updateHardChoice(Integer boardId) throws SQLException{
        return template.update(ns+"updateHardChoice",boardId);
    }

    @Override
    public int updateEasyChoice(Integer boardId) throws SQLException{
        return template.update(ns+"updateEasyChoice",boardId);
    }

    @Override
    public int checkExistTable(Integer boardId, Integer userId) throws  SQLException{
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("boardId", boardId);
        map.put("userId", userId);
        return template.selectOne(ns+"existTable",map);
    }
}
