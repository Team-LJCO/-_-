package com.korit.team_ljco.controller;

import com.korit.team_ljco.service.RecipeService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/recipes")
@RequiredArgsConstructor
public class RecipeController {
    private final RecipeService recipeService;

    //레시피 전체 조회 컨트롤러
    @GetMapping("/all")
    public ResponseEntity<?> getAllRecipes() {
        return ResponseEntity.ok(recipeService.findAllRecipes());
    }

}
