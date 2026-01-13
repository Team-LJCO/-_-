package com.korit.team_ljco.mapper;

import com.korit.team_ljco.entity.Ingredient;
import com.korit.team_ljco.entity.IngredientCategory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface IngredientMapper {

    // 재료 조회
    Ingredient selectIngredientById(Integer ingId);
    
    List<Ingredient> selectAllIngredients();
    
    List<Ingredient> selectIngredientsByCategory(Integer ingCatId);
    
    List<Ingredient> searchIngredientsByName(@Param("keyword") String keyword);

    // 재료 등록/수정/삭제
    int insertIngredient(Ingredient ingredient);
    
    int updateIngredient(Ingredient ingredient);
    
    int deleteIngredient(Integer ingId);

    // 재료 카테고리 조회
    IngredientCategory selectCategoryById(Integer ingCatId);
    
    List<IngredientCategory> selectAllCategories();
    
    // 인기 재료 조회 (사용 빈도 기준)
    List<Ingredient> selectTopIngredientsByUsage(@Param("limit") int limit);
}
