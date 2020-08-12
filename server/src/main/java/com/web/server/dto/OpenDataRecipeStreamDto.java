package com.web.server.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OpenDataRecipeStreamDto {
    private int recipeId;
    private int cookingNo;
    private String cookingDc;
    private String streStepImageUrl;
    private String stepTip;
}
