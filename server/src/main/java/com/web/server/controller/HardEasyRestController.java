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


    @PostMapping(value = "/boards/{boardId}/easy/{userId}")
    public ResponseEntity<Map<String, Object>> easyChoice(@PathVariable int boardId,int presedBtn, @PathVariable int userId, HttpServletRequest req) {
        JSONObject obj = new JSONObject();
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = null;
        try {
            String token = req.getHeader("jwt-auth-token");
            String email = jwtService.getEamil(token);
            HardEasy hardEasy = new HardEasy();
            System.out.println("email : " + email + "   , boardId : " + boardId);
            hardEasy.setEmail(email);
            hardEasy.setBoardId(boardId);
            if (hardEasyService.createHardChoice(hardEasy) == null) {   // 쿼리문이 없을때
                System.out.println("mnulll");
            } else {  // 쿼리문이 존재할때
                HardEasy result = hardEasyService.createHardChoice(hardEasy);
                System.out.println(result.getGrade());

            }

//            hardEasyService.ddddd(result.getGrade());
            status = HttpStatus.OK;
            // body json add
            resultMap.put("status", status.value());
//            HardEasy hardEasy = new HardEasy();
//            hardEasy.setGrade(userId);
//            if (hardEasyService.checkExistTable(boardId,userId)==1){
//                if()


        } catch (Exception e) {
            status = HttpStatus.BAD_REQUEST;
            // body json add
            resultMap.put("status", status.value());
            resultMap.put("message", e.getMessage());

//
        }
        return new ResponseEntity<Map<String, Object>>(resultMap, status);

    }
}
