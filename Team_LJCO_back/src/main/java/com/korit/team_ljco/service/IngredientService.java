package com.korit.team_ljco.service;

import com.korit.team_ljco.entity.Ingredient;
import com.korit.team_ljco.entity.IngredientCategory;
import com.korit.team_ljco.mapper.IngredientMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class IngredientService {

    private final IngredientMapper ingredientMapper;

    /**
     * 전체 재료 조회
     */
    public List<Ingredient> getAllIngredients() {
        return ingredientMapper.selectAllIngredients();
    }

    /**
     * 재료 ID로 조회
     */
    public Ingredient getIngredientById(Integer ingId) {
        Ingredient ingredient = ingredientMapper.selectIngredientById(ingId);
        if (ingredient == null) {
            throw new RuntimeException("재료를 찾을 수 없습니다. ID: " + ingId);
        }
        return ingredient;
    }

    /**
     * 카테고리별 재료 조회
     */
    public List<Ingredient> getIngredientsByCategory(Integer ingCatId) {
        return ingredientMapper.selectIngredientsByCategory(ingCatId);
    }

    /**
     * 재료명 검색
     */
    public List<Ingredient> searchIngredients(String keyword) {
        return ingredientMapper.searchIngredientsByName(keyword);
    }

    /**
     * 재료 등록
     */
    @Transactional
    public Ingredient createIngredient(Ingredient ingredient) {
        ingredientMapper.insertIngredient(ingredient);
        return ingredientMapper.selectIngredientById(ingredient.getIngId());
    }

    /**
     * 재료 수정
     */
    @Transactional
    public Ingredient updateIngredient(Integer ingId, Ingredient ingredient) {
        Ingredient existingIngredient = ingredientMapper.selectIngredientById(ingId);
        if (existingIngredient == null) {
            throw new RuntimeException("재료를 찾을 수 없습니다. ID: " + ingId);
        }

        ingredient.setIngId(ingId);
        ingredientMapper.updateIngredient(ingredient);
        return ingredientMapper.selectIngredientById(ingId);
    }

    /**
     * 재료 삭제
     */
    @Transactional
    public void deleteIngredient(Integer ingId) {
        Ingredient ingredient = ingredientMapper.selectIngredientById(ingId);
        if (ingredient == null) {
            throw new RuntimeException("재료를 찾을 수 없습니다. ID: " + ingId);
        }
        ingredientMapper.deleteIngredient(ingId);
    }

    /**
     * 전체 카테고리 조회
     */
    public List<IngredientCategory> getAllCategories() {
        return ingredientMapper.selectAllCategories();
    }

    /**
     * 카테고리 ID로 조회
     */
    public IngredientCategory getCategoryById(Integer ingCatId) {
        IngredientCategory category = ingredientMapper.selectCategoryById(ingCatId);
        if (category == null) {
            throw new RuntimeException("카테고리를 찾을 수 없습니다. ID: " + ingCatId);
        }
        return category;
    }

    /**
     * 인기 재료 조회 (사용 빈도 기준)
     */
    public List<Ingredient> getPopularIngredients(int limit) {
        return ingredientMapper.selectTopIngredientsByUsage(limit);
    }
}
