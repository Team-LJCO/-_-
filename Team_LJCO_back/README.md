# 🍳 냉장고 파먹기 (FridgeMate) - 레시피 추천 시스템

사용자가 보유한 재료를 기반으로 만들 수 있는 레시피를 추천하는 웹 애플리케이션입니다.

## 📋 프로젝트 개요

- **프로젝트명**: 냉장고 파먹기 (FridgeMate)
- **개발 기간**: 2024
- **팀명**: Team LJCO
- **기술 스택**: Spring Boot, MyBatis, MySQL, OAuth2, JWT

## 🛠 기술 스택

### Backend
- **Java 17**
- **Spring Boot 3.2.0**
- **Spring Security + OAuth2** (Google, Naver, Kakao)
- **JWT** (JSON Web Token)
- **MyBatis 3.0.3**
- **MySQL 8.0**

### Build Tool
- **Maven**

## 🔧 수정된 주요 문제점

### 1. MyBatis 매퍼 경로 수정
**문제**: `application.yml`의 매퍼 경로가 실제 디렉토리 구조와 불일치
- 변경 전: `classpath:mappers/**/*.xml`
- 변경 후: `classpath:mapper/**/*.xml`

### 2. Mapper 인터페이스 및 XML 파일 생성
다음 매퍼 파일들이 주석 처리되어 있어 새로 작성:
- ✅ `IngredientMapper.java` & `IngredientMapper.xml`
- ✅ `RecipeMapper.java` & `RecipeMapper.xml`
- ✅ `UserIngredientMapper.java` & `UserIngredientMapper.xml`

### 3. Service 레이어 구현
- ✅ `IngredientService.java` - 재료 관리 서비스
- ✅ `RecipeService.java` - 레시피 관리 서비스
- ✅ `UserIngredientService.java` - 사용자 재료 관리 서비스
- ✅ `UserServiceImpl.java` 패키지 경로 수정

### 4. Controller 레이어 구현
- ✅ `IngredientController.java` - 재료 API
- ✅ `RecipeController.java` - 레시피 API
- ✅ `UserIngredientController.java` - 사용자 재료 API

### 5. DTO 클래스 생성
- ✅ `OAuth2Response.java` - OAuth2 인증 응답
- ✅ `RecipeRequest.java` - 레시피 생성/수정 요청
- ✅ `RecipeResponse.java` - 레시피 응답
- ✅ `UserIngredientRequest.java` - 사용자 재료 요청

### 6. 빌드 파일 생성
- ✅ `pom.xml` - Maven 빌드 설정 파일 생성

## 📁 프로젝트 구조

```
team-ljco/
├── src/
│   └── main/
│       ├── java/
│       │   └── com/korit/team_ljco/
│       │       ├── config/
│       │       │   └── SecurityConfig.java
│       │       ├── controller/
│       │       │   ├── AuthController.java
│       │       │   ├── IngredientController.java
│       │       │   ├── RecipeController.java
│       │       │   └── UserIngredientController.java
│       │       ├── dto/
│       │       │   ├── OAuth2Response.java
│       │       │   ├── RecipeRequest.java
│       │       │   ├── RecipeResponse.java
│       │       │   └── UserIngredientRequest.java
│       │       ├── entity/
│       │       │   ├── Ingredient.java
│       │       │   ├── IngredientCategory.java
│       │       │   ├── Recipe.java
│       │       │   ├── RecipeIngredient.java
│       │       │   ├── RecipeStep.java
│       │       │   ├── User.java
│       │       │   └── UserIngredient.java
│       │       ├── filter/
│       │       │   └── JwtAuthenticationFilter.java
│       │       ├── jwt/
│       │       │   └── JwtTokenProvider.java
│       │       ├── mapper/
│       │       │   ├── IngredientMapper.java
│       │       │   ├── RecipeMapper.java
│       │       │   ├── UserIngredientMapper.java
│       │       │   └── UserMapper.java
│       │       ├── security/
│       │       │   ├── JwtAuthenticationEntryPoint.java
│       │       │   ├── OAuth2SuccessHandler.java
│       │       │   ├── PrincipalUser.java
│       │       │   └── oauth2/
│       │       │       ├── GoogleOAuth2UserInfo.java
│       │       │       ├── KakaoOAuth2UserInfo.java
│       │       │       ├── NaverOAuth2UserInfo.java
│       │       │       ├── OAuth2UserInfo.java
│       │       │       └── OAuth2UserInfoFactory.java
│       │       ├── service/
│       │       │   ├── CustomOAuth2UserService.java
│       │       │   ├── IngredientService.java
│       │       │   ├── RecipeService.java
│       │       │   ├── UserIngredientService.java
│       │       │   ├── UserService.java
│       │       │   └── UserServiceImpl.java
│       │       └── TeamLjcoApplication.java
│       └── resources/
│           ├── mapper/
│           │   ├── IngredientMapper.xml
│           │   ├── RecipeMapper.xml
│           │   ├── UserIngredientMapper.xml
│           │   └── UserMapper.xml
│           ├── application.yml
│           └── application-secret.yml
└── pom.xml
```

## 🗄 데이터베이스 스키마

### 주요 테이블

#### users (사용자)
```sql
CREATE TABLE users (
    user_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_name VARCHAR(100) NOT NULL,
    user_email VARCHAR(255) NOT NULL UNIQUE,
    user_role VARCHAR(20) DEFAULT 'USER',
    oauth2_provider VARCHAR(20),
    oauth2_id VARCHAR(255) UNIQUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
```

#### ingredients (재료)
```sql
CREATE TABLE ingredients (
    ing_id INT AUTO_INCREMENT PRIMARY KEY,
    ing_name VARCHAR(100) NOT NULL UNIQUE,
    ing_cat_id INT,
    ing_img_url VARCHAR(500),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (ing_cat_id) REFERENCES ingredient_categories(ing_cat_id)
);
```

#### recipes (레시피)
```sql
CREATE TABLE recipes (
    rcp_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    rcp_name VARCHAR(200) NOT NULL,
    rcp_desc TEXT,
    rcp_img_url VARCHAR(500),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
```

#### user_ingredients (사용자 보유 재료)
```sql
CREATE TABLE user_ingredients (
    user_ing_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    ing_id INT NOT NULL,
    user_ing_amt VARCHAR(50),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE,
    FOREIGN KEY (ing_id) REFERENCES ingredients(ing_id) ON DELETE CASCADE,
    UNIQUE KEY unique_user_ingredient (user_id, ing_id)
);
```

## 🚀 실행 방법

### 1. 데이터베이스 설정
```sql
CREATE DATABASE team_ljco_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```

### 2. application-secret.yml 설정
```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/team_ljco_db
    username: root
    password: your_password

jwt:
  secret: your-256-bit-secret-key-here

spring:
  security:
    oauth2:
      client:
        registration:
          google:
            client-id: your-google-client-id
            client-secret: your-google-secret
          naver:
            client-id: your-naver-client-id
            client-secret: your-naver-secret
          kakao:
            client-id: your-kakao-client-id
            client-secret: your-kakao-secret
```

### 3. 프로젝트 빌드 및 실행
```bash
# 프로젝트 빌드
mvn clean install

# 애플리케이션 실행
mvn spring-boot:run
```

### 4. API 문서 확인
애플리케이션 실행 후:
- Swagger UI: http://localhost:8080/doc
- API Docs: http://localhost:8080/api-docs

## 📡 주요 API 엔드포인트

### 인증 (Authentication)
- `GET /oauth2/authorization/{provider}` - OAuth2 로그인 (google, naver, kakao)

### 재료 (Ingredients)
- `GET /api/ingredients` - 전체 재료 조회
- `GET /api/ingredients/{ingId}` - 재료 상세 조회
- `GET /api/ingredients/search?keyword={keyword}` - 재료 검색
- `GET /api/ingredients/categories` - 카테고리 목록
- `GET /api/ingredients/popular?limit=10` - 인기 재료

### 레시피 (Recipes)
- `GET /api/recipes` - 전체 레시피 조회
- `GET /api/recipes/{rcpId}` - 레시피 상세 조회
- `GET /api/recipes/search?keyword={keyword}` - 레시피 검색
- `GET /api/recipes/recommended?userId={userId}` - 추천 레시피
- `POST /api/recipes` - 레시피 등록
- `PUT /api/recipes/{rcpId}` - 레시피 수정
- `DELETE /api/recipes/{rcpId}` - 레시피 삭제

### 사용자 재료 (User Ingredients) - 인증 필요
- `GET /api/user/ingredients` - 내 재료 조회
- `POST /api/user/ingredients` - 재료 추가
- `PUT /api/user/ingredients/{userIngId}` - 재료 수정
- `DELETE /api/user/ingredients/{userIngId}` - 재료 삭제

## 🔐 인증 방식

1. **OAuth2 소셜 로그인**
   - Google, Naver, Kakao 지원
   - 로그인 성공 시 JWT 토큰 발급

2. **JWT 인증**
   - Access Token을 통한 API 인증
   - Header: `Authorization: Bearer {token}`

## ⚙️ 핵심 기능

### 1. 레시피 추천 알고리즘
사용자가 보유한 재료와 레시피 재료를 매칭하여 추천:
```sql
SELECT 
    r.rcp_id,
    r.rcp_name,
    COUNT(DISTINCT ri.ing_id) AS totalIngredients,
    COUNT(DISTINCT ui.ing_id) AS matchedIngredients,
    ROUND(COUNT(DISTINCT ui.ing_id) * 100.0 / COUNT(DISTINCT ri.ing_id), 2) AS matchPercentage
FROM recipes r
INNER JOIN recipe_ingredients ri ON r.rcp_id = ri.rcp_id
LEFT JOIN user_ingredients ui ON ri.ing_id = ui.ing_id AND ui.user_id = ?
GROUP BY r.rcp_id
HAVING matchedIngredients > 0
ORDER BY matchPercentage DESC, matchedIngredients DESC
```

### 2. 재료 카테고리별 관리
- 채소류, 육류, 해산물, 조미료 등 카테고리별 분류
- 카테고리별 재료 조회 및 관리

### 3. OAuth2 소셜 로그인
- 복수 소셜 계정 연동 지원
- 자동 회원가입 및 정보 업데이트

## 🔧 다음 단계 작업 사항

1. **프론트엔드 개발**
   - React/Vue.js를 이용한 UI 구현
   - 레시피 추천 화면
   - 재료 관리 화면

2. **추가 기능**
   - 레시피 북마크/좋아요
   - 레시피 리뷰 및 평점
   - 조리 타이머 기능

3. **성능 최적화**
   - Redis 캐싱 적용
   - 페이지네이션 구현
   - 이미지 최적화

4. **테스트 코드 작성**
   - Unit Test
   - Integration Test
   - API Test

## 📝 라이선스

이 프로젝트는 교육 목적으로 제작되었습니다.

## 👥 팀원

- Team LJCO

## 📞 문의

프로젝트 관련 문의사항이 있으시면 이슈를 등록해주세요.
