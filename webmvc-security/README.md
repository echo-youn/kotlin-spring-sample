# Security

# JPA

- datasource
- driver
- h2
- in memory
- console enabled

# SQL

- spring.sql.init
- data.sql, schema.sql
- jpa.hibernate.ddl-auto

# OSIV

- setting - false

# Security

- CSRF disabled (not for browser)
- `filterChain bean 참고`
- kotlin dsl with `org.springframework.security.config.annotation.web.invoke`
- `JWT Token Filter`와 Security 설정
- SecurityManager, PasswordEncoder 참고
- `argon2Password`사용하기 위해서 `implementation("org.bouncycastle:bcprov-jdk18on:1.74")` 추가

## Authorization
- 데이터는 기본 `ROLE_` 접두어를 붙여야한다.
- security config에서 `authorize("/secured/admin", hasRole("ADMIN"))`로 설정 가능
- `@EnableMethodSecurity`를 사용하고 `secured = true`를 설정할 경우 메서드 사용 가능
- `@Secured("ROLE_ADMIN")`와 `@PreAuthorize("hasRole('ADMIN')")` 다음과 같이 사용하면 됨

## ROLE Hierarcy
- `RoleHierarchy`빈 참고

# H2

- encodePassword
- webSecurity ignore...
