package com.web.server.repo;

import com.web.server.dto.FollowDto;

import java.sql.SQLException;

public interface FollowDao {
    public int insert(FollowDto follow);

    public int checkFollow(int follower, int followee) throws SQLException;
}
