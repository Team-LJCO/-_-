package com.korit.team_ljco.filter;

import com.korit.team_ljco.entity.User;
import com.korit.team_ljco.jwt.JwtTokenProvider;
import com.korit.team_ljco.service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;

    // JWT 인증을 건너뛸 경로 목록
    private static final List<String> EXCLUDED_PATHS = Arrays.asList(
            "/doc",
            "/swagger-ui",
            "/v3/api-docs",
            "/api-docs",
            "/swagger-resources",
            "/webjars",
            "/api/auth",
            "/oauth2",
            "/login",
            "/api/ingredients",
            "/api/recipes"
    );

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // 공개 경로는 JWT 검증 건너뛰기
        String requestPath = request.getRequestURI();
        if (isPublicPath(requestPath)) {
            filterChain.doFilter(request, response);
            return;
        }

        // 요청 헤더에서 JWT 토큰 추출
        String token = getTokenFromRequest(request);

        // 토큰 유효성 검증
        if (StringUtils.hasText(token) && jwtTokenProvider.validateToken(token)) {
            // 토큰에서 사용자 ID 추출
            Long userId = jwtTokenProvider.getUserIdFromToken(token);

            // DB에서 사용자 조회
            User user = userService.findUserById(userId);

            if (user != null) {
                // Spring Security 인증 객체 생성
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(
                                user,
                                null,
                                List.of(new SimpleGrantedAuthority("ROLE_" + user.getUserRole()))
                        );

                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                // SecurityContext에 인증 정보 설정
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        filterChain.doFilter(request, response);
    }

    /**
     * 공개 경로인지 확인
     */
    private boolean isPublicPath(String requestPath) {
        return EXCLUDED_PATHS.stream()
                .anyMatch(requestPath::startsWith);
    }

    /**
     * HTTP 요청 헤더에서 Bearer 토큰 추출
     */
    private String getTokenFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");

        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }

        return null;
    }
}