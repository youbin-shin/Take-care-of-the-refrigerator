package com.web.server.util;

import java.net.URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.web.server.config.OpenApiProperties;

@Component
public class OpenApiClient {

	@Autowired
	OpenApiProperties properties;
	
	public void Test() {
		
		String baseUrl = properties.getFoodsafeUrl();
		String key = properties.getFoodafeKey();
		
		StringBuilder reqUrl = new StringBuilder();
		reqUrl.append(baseUrl).append(key).append("/").append("COOKRCP01");
//		String urlApi = 
	}
}
