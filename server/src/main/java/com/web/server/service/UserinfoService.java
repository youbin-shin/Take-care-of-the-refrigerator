package com.web.server.service;

import com.web.server.dto.Board;
import com.web.server.dto.FollowDto;
import com.web.server.dto.Steps;
import com.web.server.dto.User;
import com.web.server.dto.UserProfileDto;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Map;


public interface UserinfoService {

    public User login(String email, String password);

    // c
    public int insertUser(User user);

    public int insertFollow(FollowDto follow) throws SQLException;

    // r
    public boolean checkAccount(String nickname) throws SQLException;

    public boolean checkAccount(String email, String nickname) throws SQLException;

    public boolean checkFollow(String email, String nickname) throws SQLException;

    public User searchByEmail(String email);

    public User searchByNickName(String nickname);

    public UserProfileDto searchUserProfileByEmail(String email) throws SQLException;

    public List<Map<String, Object>> searchFollowList(Map<String, String> followMap);

    // u
    public int modify(User user) throws SQLException;

    public int updateUser(User user) throws SQLException;

    // d
    public int deleteUser(String email) throws SQLException;

    public int deleteFollow(String email, String nickname) throws SQLException;

	public List<Board> searchAllInterestedFoodSafeRecipesFormBoard(String email) throws SQLException;
	
	// 사용자가 저장한 steps
	// Create
	public int addBoardStep(String email, Steps step) throws SQLException;
	
	// Read
	
	// Delete
}
