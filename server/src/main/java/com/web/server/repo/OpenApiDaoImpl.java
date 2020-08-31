package com.web.server.repo;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.web.server.dto.FoodSafeRecipeDto;

@Repository
public class OpenApiDaoImpl implements OpenApiDao{
	
	private String ns = "com.web.server.repo.OpenApiDao.";
	
	@Autowired
	SqlSessionTemplate template;

	@Override
	public List<FoodSafeRecipeDto> selectAll() {
		List<FoodSafeRecipeDto> result = null;
		result = template.selectList(ns + "selectAll");
		return result;
	}

	@Override
	public List<FoodSafeRecipeDto> selectByRecipeSeq(int rcpSeq) {
		List<FoodSafeRecipeDto> result = null;
		result = template.selectList(ns + "selectByRcpSeq", rcpSeq);
		return result;
	}

}
