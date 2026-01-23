package com.korit.team_ljco.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RecipeRequest {
    
    private String rcpName;
    private String rcpDesc;
    private String rcpImgUrl;
    private List<RecipeIngredientDto> ingredients;
    private List<RecipeStepDto> steps;
    
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RecipeIngredientDto {
        private Integer ingId;
        private String rcpIngAmt;
        private Integer rcpIngOrd;
        private boolean hasIng;
    }
    
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RecipeStepDto {
        private Integer stepNo;
        private String stepDesc;
        private String stepImgUrl;
    }
}
