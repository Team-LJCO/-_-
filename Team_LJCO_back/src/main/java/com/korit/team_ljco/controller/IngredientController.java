package com.korit.team_ljco.controller;

import com.korit.team_ljco.entity.Ingredient;
import com.korit.team_ljco.entity.IngredientCategory;
import com.korit.team_ljco.service.IngredientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Ingredients", description = "재료 관리 API")
@RestController
@RequestMapping("/api/ingredients")
@RequiredArgsConstructor
public class IngredientController {

    private final IngredientService ingredientService;

    @Operation(summary = "전체 재료 조회", description = "데이터베이스에 등록된 모든 재료를 조회합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "조회 성공"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    @GetMapping
    public ResponseEntity<List<Ingredient>> getAllIngredients() {
        List<Ingredient> ingredients = ingredientService.getAllIngredients();
        return ResponseEntity.ok(ingredients);
    }

    @Operation(summary = "재료 상세 조회", description = "재료 ID로 특정 재료의 상세 정보를 조회합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "조회 성공"),
            @ApiResponse(responseCode = "404", description = "재료를 찾을 수 없음"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    @GetMapping("/{ingId}")
    public ResponseEntity<Ingredient> getIngredientById(
            @Parameter(description = "재료 ID", example = "1", required = true)
            @PathVariable Integer ingId) {
        Ingredient ingredient = ingredientService.getIngredientById(ingId);
        return ResponseEntity.ok(ingredient);
    }

    @Operation(summary = "카테고리별 재료 조회", description = "특정 카테고리에 속한 재료들을 조회합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "조회 성공"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    @GetMapping("/category/{ingCatId}")
    public ResponseEntity<List<Ingredient>> getIngredientsByCategory(
            @Parameter(description = "카테고리 ID", example = "1", required = true)
            @PathVariable Integer ingCatId) {
        List<Ingredient> ingredients = ingredientService.getIngredientsByCategory(ingCatId);
        return ResponseEntity.ok(ingredients);
    }

    @Operation(summary = "재료 검색", description = "재료명으로 검색합니다. (부분 일치)")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "검색 성공"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    @GetMapping("/search")
    public ResponseEntity<List<Ingredient>> searchIngredients(
            @Parameter(description = "검색 키워드", example = "양파", required = true)
            @RequestParam String keyword) {
        List<Ingredient> ingredients = ingredientService.searchIngredients(keyword);
        return ResponseEntity.ok(ingredients);
    }

    @Operation(summary = "재료 등록", description = "새로운 재료를 등록합니다. (관리자 기능)")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "등록 성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 요청"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    @PostMapping
    public ResponseEntity<Ingredient> createIngredient(
            @Parameter(description = "등록할 재료 정보", required = true)
            @RequestBody Ingredient ingredient) {
        Ingredient createdIngredient = ingredientService.createIngredient(ingredient);
        return ResponseEntity.ok(createdIngredient);
    }

    @Operation(summary = "재료 수정", description = "기존 재료 정보를 수정합니다. (관리자 기능)")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "수정 성공"),
            @ApiResponse(responseCode = "404", description = "재료를 찾을 수 없음"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    @PutMapping("/{ingId}")
    public ResponseEntity<Ingredient> updateIngredient(
            @Parameter(description = "재료 ID", example = "1", required = true)
            @PathVariable Integer ingId,
            @Parameter(description = "수정할 재료 정보", required = true)
            @RequestBody Ingredient ingredient) {
        Ingredient updatedIngredient = ingredientService.updateIngredient(ingId, ingredient);
        return ResponseEntity.ok(updatedIngredient);
    }

    @Operation(summary = "재료 삭제", description = "재료를 삭제합니다. (관리자 기능)")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "삭제 성공"),
            @ApiResponse(responseCode = "404", description = "재료를 찾을 수 없음"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    @DeleteMapping("/{ingId}")
    public ResponseEntity<Void> deleteIngredient(
            @Parameter(description = "재료 ID", example = "1", required = true)
            @PathVariable Integer ingId) {
        ingredientService.deleteIngredient(ingId);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "전체 카테고리 조회", description = "재료 카테고리 목록을 조회합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "조회 성공"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    @GetMapping("/categories")
    public ResponseEntity<List<IngredientCategory>> getAllCategories() {
        List<IngredientCategory> categories = ingredientService.getAllCategories();
        return ResponseEntity.ok(categories);
    }

    @Operation(summary = "카테고리 상세 조회", description = "카테고리 ID로 특정 카테고리 정보를 조회합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "조회 성공"),
            @ApiResponse(responseCode = "404", description = "카테고리를 찾을 수 없음"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    @GetMapping("/categories/{ingCatId}")
    public ResponseEntity<IngredientCategory> getCategoryById(
            @Parameter(description = "카테고리 ID", example = "1", required = true)
            @PathVariable Integer ingCatId) {
        IngredientCategory category = ingredientService.getCategoryById(ingCatId);
        return ResponseEntity.ok(category);
    }

    @Operation(summary = "인기 재료 조회", description = "레시피에서 가장 많이 사용되는 재료 목록을 조회합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "조회 성공"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    @GetMapping("/popular")
    public ResponseEntity<List<Ingredient>> getPopularIngredients(
            @Parameter(description = "조회할 재료 개수", example = "10")
            @RequestParam(defaultValue = "10") int limit) {
        List<Ingredient> ingredients = ingredientService.getPopularIngredients(limit);
        return ResponseEntity.ok(ingredients);
    }
}