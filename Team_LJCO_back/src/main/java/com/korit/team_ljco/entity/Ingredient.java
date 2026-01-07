package com.korit.team_ljco.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Ingredient {
    private Long ingredientId;
    private String name;
    private String imageUrl;
    private LocalDateTime createdAt;

    // 객체 대신 FK 번호를 그대로 넣습니다.
    private Long categoryId;
}