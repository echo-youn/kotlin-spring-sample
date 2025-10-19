# Webflux Security

Spring boot 4.0.0 M

## Security Config
해당 파일에 주석 많이 달아놈


1. SecurityWebFilterChain Bean 생성 (ServerHttpSecurity 주입 및 위임)
2. security Matcher로 위 Chain을 적용한 경로 설정
3. (Optional) 프론트 스프링 앱이라면 CSRF 설정
4. (Optional) cors 필요한 경우 설정
5. (Optional) formLogin 로그인 창 필요시 설정
6. (Optional) HTTP Basic 방식 로그인 사용시 활성화
7. AuthenticationManager 구현
8. Filter 구현, Authentication Manager 주입 및 사용
7. authorizeExchange로 권한(Authorization) 설정
8. (Optional) AuthorizationManager 생성하여 사용
9. Error Handling에 로그인페이지, 에러 변경
10. (Optional) securityContextRepository NoOps레파지토리로 설정