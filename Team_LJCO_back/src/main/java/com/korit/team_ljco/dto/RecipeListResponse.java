package com.korit.team_ljco.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RecipeListResponse {
    //레시피 화면 목록 내려줄 형태
    private Long rcpId;
    private String rcpName;
    private String rcpImgUrl;
    private Integer rcpViewCount;

    //난이도 출력
    private Integer level;

    //재료 출력
    private List<IngredientName> ingredientName;

    //일치율
    private Integer matchRate;


    //오래된 재료의 날짜
    private LocalDate oldestIngredient;

    //서비스에서 계산된 날짜
    private int passedDay;





}
