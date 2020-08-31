package com.web.server.service;

<<<<<<< server/src/main/java/com/web/server/service/BoardService.java
import com.web.server.dto.Board;
import com.web.server.dto.BoardSearchByFoodList;
import com.web.server.dto.BoardSimpleDto;
import com.web.server.dto.CommentDto;
import com.web.server.dto.FoodSafeRecipeDto;
=======
import com.web.server.dto.*;
>>>>>>> server/src/main/java/com/web/server/service/BoardService.java

import java.sql.SQLException;
import java.util.List;

public interface BoardService {
    List<BoardSimpleDto> searchAll(String email) throws SQLException;

    Board searchByBoardId(int boardId) throws SQLException;

    boolean write(String email, Board board) throws SQLException;

    int deleteBoard(int boardId) throws SQLException;

    void writeComment(CommentDto comment) throws SQLException;

    void updateComment(CommentDto comment) throws SQLException;

    int deleteComment(Integer commentId) throws SQLException;

    List<CommentDto> selectCommentByBoardId(int boardId);

    List<BoardSimpleDto> searchAllByFood(BoardSearchByFoodList foodList) throws SQLException;

    void updateViewCnt(Integer boardId) throws SQLException;

    int postFavorite(String email, FavoriteRequestBody boardId) throws SQLException;

    List<BoardSimpleDto> searchByKeyword(String email, SearchByKeywordDto searchByKeywordDto) throws SQLException;
    
    public List<FoodSafeRecipeDto> searchAllFoodSafeRecipes() throws SQLException;
    
    public List<FoodSafeRecipeDto> searchFoodSafeRecipesByRecipeSeq(int rcpSeq) throws SQLException;
    
}
