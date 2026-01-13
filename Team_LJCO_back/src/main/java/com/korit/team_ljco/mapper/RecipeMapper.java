package com.korit.team_ljco.mapper;


import com.korit.team_ljco.entity.Recipe;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RecipeMapper {
    //레시피 전체 조회
    List<Recipe> getRecipes();

}
