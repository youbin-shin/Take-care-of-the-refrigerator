package com.web.server.dto;

import java.util.List;

public class UserProfileDto {
    // users table
    private String nickname;
    private String introduce;
    private String box;
    // follow table
    private String followingCount; // 내가 follow
    private String followerCount;  // 남이 나를 follow
    // boards table
    private List<Board> myBoards;
    // interest_boards
    private List<Board> interestBoards;
}
