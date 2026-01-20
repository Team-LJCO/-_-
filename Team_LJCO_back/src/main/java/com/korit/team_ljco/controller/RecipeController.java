package com.korit.team_ljco.controller;
import com.korit.team_ljco.dto.RecipeListResponse;
import com.korit.team_ljco.entity.RecipeStep;
import com.korit.team_ljco.service.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recipes") // 앞에 / 추가
@RequiredArgsConstructor
public class RecipeController {
    private final RecipeService recipeService;

    // 레시피 목록 조회 컨트롤러
    @GetMapping
    public List<RecipeListResponse> getAllRecipes(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam int userId) {
        return recipeService.findRecipes(page, userId);
    }

    // RecipeController.java
    @GetMapping("/search")
    public List<RecipeListResponse> searchRecipes(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam int userId,
            @RequestParam String keyword) { // 프론트에서 넘어올 검색어

        // 서비스의 새로운 검색 메서드 호출
        return recipeService.searchRecipesByKeyword(page, userId, keyword);
    }

    // RecipeController.java 파일에 추가
    @GetMapping("/{rcpId}/steps")
    public List<RecipeStep> getRecipeSteps(@PathVariable Long rcpId) {
        return recipeService.getRecipeSteps(rcpId); // 서비스 호출
    }

}
