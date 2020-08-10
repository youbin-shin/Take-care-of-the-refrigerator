package com.web.server.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.logging.Logger;

@RequestMapping("/api")
@RestController
public class FileUploadRestController {

    private static final Logger logger = Logger.getLogger(FileUploadRestController.class.getName());

    // https://www.roytuts.com/file-upload-example-using-spring-rest-controller/
    @PostMapping("/upload")
    public ResponseEntity<String > uploadData(@RequestParam("file")MultipartFile file) throws  Exception {

        return null;
    }
}
