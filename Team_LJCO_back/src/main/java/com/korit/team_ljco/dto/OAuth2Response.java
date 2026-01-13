package com.korit.team_ljco.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OAuth2Response {
    
    private String accessToken;
    private String tokenType;
    private Long userId;
    private String userName;
    private String userEmail;
    private String userRole;
}
