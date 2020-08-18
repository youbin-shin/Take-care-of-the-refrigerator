package com.web.server.repo;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.web.server.dto.Board;
import com.web.server.dto.BoardSimpleDto;
import com.web.server.dto.FoodSafeRecipeDto;

@Repository
public class OpenApiDaoImpl implements OpenApiDao{
	
	private String ns = "com.web.server.repo.OpenApiDao.";
	
	@Autowired
	SqlSessionTemplate template;

	@Override
	public List<BoardSimpleDto> selectAllFormBoards(String email, int page) {
		List<BoardSimpleDto> result = null;
		Map<String, Object> map = new HashMap<>();
		map.put("email", email);
		map.put("page", page);
		result = template.selectList(ns + "selectAllFormBoards", map);
		return result;
	}

	@Override
	public List<FoodSafeRecipeDto> selectByRecipeSeq(int rcpSeq) {
		List<FoodSafeRecipeDto> result = null;
		result = template.selectList(ns + "selectByRcpSeq", rcpSeq);
		return result;
	}
	
	@Override
	public List<Board> selectByRecipeNameFormBoards(String rcpNm) {
		List<Board> result = null;
		result = template.selectList(ns + "selectByRcpNmFormBoards", rcpNm);
		return result;
	}

	@Override
	public List<Board> selectByRecipePartsDtlsFormBoards(Map<String, Object> map) {
		List<Board> result = null;
		result = template.selectList(ns + "selectByRcpPartsDtlsFormBoards", map);
		return result;
	}

	// 관심 레시피 관련 함수
	/**
	 * return : true: 등록 중, false: 미등록
	 */
	@Override
	public boolean checkInterestingRecipe(String email, int boardId) {
		int result = 0;
		Map<String, Object> map = new HashMap<>();
		map.put("email", email);
		map.put("boardId", boardId);
		result = template.selectOne(ns + "isExistInterestingRecipes", map);
		return result == 0? false : true;
	}

	@Override
	public List<Board> selectAllInterestedRecipesByEamilFormBoard(String email) throws SQLException{
		List<Board> result = null;
		result = template.selectList(ns + "selectAllInterestedRecipesByEamilFormBoard", email);
		return result;
	}

	@Override
	public int insertInterestingRecipe(String email, int boardId) {
		Map<String, Object> map = new HashMap<>();
		map.put("email", email);
		map.put("boardId", boardId);
		return template.insert(ns + "insertInterestingRecipes", map);
	}

	@Override
	public int deleteInterestedRecipe(String email, int boardId) {
		Map<String, Object> map = new HashMap<>();
		map.put("email", email);
		map.put("boardId", boardId);
		return template.delete(ns + "deleteInterestedRecipe", map);
	}
	

}
