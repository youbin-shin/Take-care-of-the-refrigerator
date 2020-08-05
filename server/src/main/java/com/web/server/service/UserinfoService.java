package com.web.server.service;

import com.web.server.dto.User;
import com.web.server.dto.UserProfileDto;

import java.sql.SQLException;
import java.util.Collection;


public interface UserinfoService {

    public User login(String email, String password);

    // c
    public int insertUser(User user);

    // r
    public boolean checkAccount(String nickname) throws SQLException;

    public boolean checkAccount(String email, String nickname) throws SQLException;

    public User searchByEmail(String email);

    public UserProfileDto searchUserProfileByEmail(String email);

    // u
    public int modify(User user);

    public int updateUser(User user);

    // d
    public int deleteUser(String email);
}
