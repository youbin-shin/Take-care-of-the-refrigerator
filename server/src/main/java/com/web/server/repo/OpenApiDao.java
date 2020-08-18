package com.web.server.repo;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.web.server.dto.Board;
import com.web.server.dto.BoardSimpleDto;
import com.web.server.dto.FoodSafeRecipeDto;

public interface OpenApiDao {
	public List<BoardSimpleDto> selectAllFormBoards(String email, int page) throws SQLException;
	
	public List<FoodSafeRecipeDto> selectByRecipeSeq(int rcpSeq);
	
	public List<Board> selectByRecipeNameFormBoards(String rcpNm); 

	public List<Board> selectByRecipePartsDtlsFormBoards(Map<String, Object> map);
	
	public boolean checkInterestingRecipe(String email, int boardId);
	
	public List<Board> selectAllInterestedRecipesByEamilFormBoard(String email) throws SQLException;
	
	public int insertInterestingRecipe(String email, int boardId);
	
	public int deleteInterestedRecipe(String email, int boardId);
}
