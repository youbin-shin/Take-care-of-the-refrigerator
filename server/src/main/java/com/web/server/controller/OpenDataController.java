package com.web.server.controller;

import com.web.server.service.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.Map;

@Controller
@Slf4j
public class OpenDataController {

    private static final Logger logger = LoggerFactory.getLogger(OpenDataController.class);

    @Autowired
    RecipeService recipeService;

    public ResponseEntity<Map<String, Object>> getTest() {

        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = null;

        recipeService.test("Grid_20150827000000000228_1");

        status = HttpStatus.OK;
        resultMap.put("success", true);

        return new ResponseEntity<>(resultMap, status);
    }
}
