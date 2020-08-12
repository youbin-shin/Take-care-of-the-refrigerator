package com.web.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecipeServiceImpl implements RecipeService{

    @Autowired
    private OpenDataClient openDataClient;

    @Override
    public void test(String arg) {
        openDataClient.requestOpenData(arg);
    }
}
