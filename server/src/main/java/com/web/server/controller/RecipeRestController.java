package com.web.server.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RecipeRestController {

	@GetMapping("/recipe")
	public ResponseEntity<Map<String, Object>> getTest() {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = null;
		
		
		
		return new ResponseEntity<Map<String,Object>>(resultMap, status);
	}
}
