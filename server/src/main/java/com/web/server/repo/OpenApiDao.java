package com.web.server.repo;

import java.util.List;

import com.web.server.dto.FoodSafeRecipeDto;

public interface OpenApiDao {
	public List<FoodSafeRecipeDto> selectAll();
	
	public List<FoodSafeRecipeDto> selectByRecipeSeq(int rcpSeq);
}
