package com.web.server.controller;

import com.web.server.dto.HardEasy;
import com.web.server.service.HardEasyService;
import com.web.server.service.JwtService;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
@Slf4j
public class HardEasyRestController {

    @Autowired
    HardEasyService hardEasyService;

    @Autowired
    JwtService jwtService;


    @PostMapping(value = "/boards/{boardId}/{pressedBtn}")
    public ResponseEntity<Map<String, Object>> Choice(@PathVariable int boardId,@PathVariable int pressedBtn, HttpServletRequest req) {
        JSONObject obj = new JSONObject();
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = null;
        if (pressedBtn == 1) {
            try {
                String token = req.getHeader("jwt-auth-token");
                String email = jwtService.getEamil(token);
                HardEasy hardEasy = new HardEasy();
                hardEasy.setEmail(email);
                hardEasy.setBoardId(boardId);
                if (hardEasyService.checkChoice(hardEasy) == null) {   // 쿼리문이 없을때
                    hardEasyService.createHardChoice(hardEasy);
                } else {  // 쿼리문이 존재할때
                    HardEasy result = hardEasyService.checkChoice(hardEasy);
                    int grade = result.getGrade();
                    if (grade == 1) {
                        hardEasyService.deleteChoice(hardEasy);
                    } else {
                        hardEasyService.easyToHardChange(hardEasy);
                    }
                    System.out.println(result.getGrade());
                }

                status = HttpStatus.OK;
                resultMap.put("status", status.value());
                resultMap.put("easyCount",hardEasyService.updateEasyChoice(hardEasy));
                resultMap.put("hardCount",hardEasyService.updateHardChoice(hardEasy));
            } catch (Exception e) {
                status = HttpStatus.BAD_REQUEST;
                resultMap.put("status", status.value());
                resultMap.put("message", e.getMessage());

//
            }
        } else {
            try {
                String token = req.getHeader("jwt-auth-token");
                String email = jwtService.getEamil(token);
                HardEasy hardEasy = new HardEasy();
                hardEasy.setEmail(email);
                hardEasy.setBoardId(boardId);
                if (hardEasyService.checkChoice(hardEasy) == null) {   // 쿼리문이 없을때
                    hardEasyService.createEasyChoice(hardEasy);
                } else {  // 쿼리문이 존재할때
                    HardEasy result = hardEasyService.checkChoice(hardEasy);
                    int grade = result.getGrade();
                    if (grade == 2) {
                        hardEasyService.deleteChoice(hardEasy);
                    } else {
                        hardEasyService.hardToEasyChange(hardEasy);
                    }
                }
                status = HttpStatus.OK;
                resultMap.put("status", status.value());
                resultMap.put("easyCount",hardEasyService.updateEasyChoice(hardEasy));
                resultMap.put("hardCount",hardEasyService.updateHardChoice(hardEasy));
            } catch (Exception e) {
                status = HttpStatus.BAD_REQUEST;
                resultMap.put("status", status.value());
                resultMap.put("message", e.getMessage());
            }


            }return new ResponseEntity<Map<String, Object>>(resultMap,status);
        }
    }
