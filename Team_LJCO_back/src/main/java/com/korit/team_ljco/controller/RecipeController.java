package com.korit.team_ljco.controller;

import com.korit.team_ljco.dto.RecipeRequest;
import com.korit.team_ljco.dto.RecipeResponse;
import com.korit.team_ljco.service.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/recipes")
@RequiredArgsConstructor
public class RecipeController {

    private final RecipeService recipeService;

    /**
     * 전체 레시피 조회
     */
    @GetMapping
    public ResponseEntity<List<RecipeResponse>> getAllRecipes() {
        List<RecipeResponse> recipes = recipeService.getAllRecipes();
        return ResponseEntity.ok(recipes);
    }

    /**
     * 레시피 상세 조회
     */
    @GetMapping("/{rcpId}")
    public ResponseEntity<RecipeResponse> getRecipeById(@PathVariable Long rcpId) {
        RecipeResponse recipe = recipeService.getRecipeById(rcpId);
        return ResponseEntity.ok(recipe);
    }

    /**
     * 레시피 검색
     */
    @GetMapping("/search")
    public ResponseEntity<List<RecipeResponse>> searchRecipes(@RequestParam String keyword) {
        List<RecipeResponse> recipes = recipeService.searchRecipes(keyword);
        return ResponseEntity.ok(recipes);
    }

    /**
     * 레시피 등록
     */
    @PostMapping
    public ResponseEntity<RecipeResponse> createRecipe(@RequestBody RecipeRequest request) {
        RecipeResponse recipe = recipeService.createRecipe(request);
        return ResponseEntity.ok(recipe);
    }

    /**
     * 레시피 수정
     */
    @PutMapping("/{rcpId}")
    public ResponseEntity<RecipeResponse> updateRecipe(
            @PathVariable Long rcpId,
            @RequestBody RecipeRequest request) {
        RecipeResponse recipe = recipeService.updateRecipe(rcpId, request);
        return ResponseEntity.ok(recipe);
    }

    /**
     * 레시피 삭제
     */
    @DeleteMapping("/{rcpId}")
    public ResponseEntity<Void> deleteRecipe(@PathVariable Long rcpId) {
        recipeService.deleteRecipe(rcpId);
        return ResponseEntity.noContent().build();
    }

    /**
     * 추천 레시피 조회 (사용자 재료 기반)
     */
    @GetMapping("/recommended")
    public ResponseEntity<List<Map<String, Object>>> getRecommendedRecipes(
            @RequestParam Long userId) {
        List<Map<String, Object>> recipes = recipeService.getRecommendedRecipes(userId);
        return ResponseEntity.ok(recipes);
    }

    /**
     * 인기 레시피 조회
     */
    @GetMapping("/popular")
    public ResponseEntity<List<RecipeResponse>> getPopularRecipes(
            @RequestParam(defaultValue = "10") int limit) {
        List<RecipeResponse> recipes = recipeService.getPopularRecipes(limit);
        return ResponseEntity.ok(recipes);
    }

    /**
     * 레시피 총 개수
     */
    @GetMapping("/count")
    public ResponseEntity<Integer> getTotalRecipeCount() {
        int count = recipeService.getTotalRecipeCount();
        return ResponseEntity.ok(count);
    }
}
