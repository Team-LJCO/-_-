package com.korit.team_ljco.service;

import com.korit.team_ljco.dto.RecipeRequest;
import com.korit.team_ljco.dto.RecipeResponse;
import com.korit.team_ljco.entity.Recipe;
import com.korit.team_ljco.entity.RecipeIngredient;
import com.korit.team_ljco.entity.RecipeStep;
import com.korit.team_ljco.mapper.RecipeMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class RecipeService {

    private final RecipeMapper recipeMapper;

    //전체 레시피 조회
    public List<Recipe> findAllRecipes() {
        return recipeMapper.getRecipes();

    }

}
