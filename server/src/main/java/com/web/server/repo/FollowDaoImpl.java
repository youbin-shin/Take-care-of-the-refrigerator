package com.web.server.repo;

import com.web.server.dto.FollowDto;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class FollowDaoImpl implements FollowDao {
    private String ns = "com.web.server.dto.User.";

    @Autowired
    SqlSessionTemplate template;

    @Override
    public int insert(FollowDto follow) {
        return template.insert(ns + "insertFollow", follow);
    }
}
