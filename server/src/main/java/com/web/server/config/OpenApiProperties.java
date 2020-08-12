package com.web.server.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "opendata")
public class OpenApiProperties {

	private String foodafeKey;
	private String foodsafeUrl;
    private String mafraKey;
    private String mafraUrl;
    
	public String getFoodafeKey() {
		return foodafeKey;
	}
	public void setFoodafeKey(String foodafeKey) {
		this.foodafeKey = foodafeKey;
	}
	public String getFoodsafeUrl() {
		return foodsafeUrl;
	}
	public void setFoodsafeUrl(String foodsafeUrl) {
		this.foodsafeUrl = foodsafeUrl;
	}
	public String getMafraKey() {
		return mafraKey;
	}
	public void setMafraKey(String mafraKey) {
		this.mafraKey = mafraKey;
	}
	public String getMafraUrl() {
		return mafraUrl;
	}
	public void setMafraUrl(String mafraUrl) {
		this.mafraUrl = mafraUrl;
	}

    
}
