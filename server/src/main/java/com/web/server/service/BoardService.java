package com.web.server.service;

import com.web.server.dto.Board;
import com.web.server.dto.BoardSearchByFoodList;
import com.web.server.dto.BoardSimpleDto;
import com.web.server.dto.CommentDto;

import java.sql.SQLException;
import java.util.List;

public interface BoardService {
    public List<BoardSimpleDto> searchAll() throws SQLException;

    public Board searchByBoardId(int boardId) throws SQLException;

    public boolean write(String email, Board board) throws SQLException;

    int deleteBoard(int boardId) throws SQLException;

    public void writeComment(CommentDto comment) throws SQLException;

    public void updateComment(CommentDto comment) throws SQLException;

    public int deleteComment(Integer commentId) throws SQLException;

    List<CommentDto> selectCommentByBoardId(int boardId);

    List<BoardSimpleDto> searchAllByFood(BoardSearchByFoodList foodList) throws SQLException;

    public void updateViewCnt(Integer boardId) throws SQLException;
}
