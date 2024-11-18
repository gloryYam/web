package com.glory.chatapp.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.List;

/**
 * 1. 유효성 검증 메서드 추가
 * <p>
 * 2. 사용자 지정 클레임 암호화 : 사용자 정의 클레임을 암호화
 * <p>
 * 3. 역할(Role) 검증 : 특정 API를 호출할 때 roles를 확인하여 권한이 없는 경우 접근을 제한
 * <p>
 * 4. Refresh Token을 사용해 Access Token을 재발급할 때, 재사용 공격을 방지하기 위해 Refresh Token을 재발급 시 새로운 Refresh Token으로 갱신하는 것이 좋습니다.
 * <p>
 * 5. 예외 처리 추가 : 현재 JWT 파싱 과정에서 예외 처리가 없습니다.
 */
@Slf4j
@Component
public class JwtProvider {

    private final String jwtSecretKey;
    private final long accessTokenExpiredTime;
    private final long refreshTokenExpiredTime;

    public JwtProvider(
            @Value("${jwt.secret-key}") String jwtSecretKey,
            @Value("${jwt.access-token-expired-time}") long accessTokenExpiredTime,
            @Value("${jwt.refresh-token-expired-time}") long refreshTokenExpiredTime
    ) {
        this.jwtSecretKey = jwtSecretKey;
        this.accessTokenExpiredTime = accessTokenExpiredTime;
        this.refreshTokenExpiredTime = refreshTokenExpiredTime;
    }

    /**
     * 서명에 사용할 비밀키
     */
    private Key getSecretKey() {
        byte[] keyBytes = jwtSecretKey.getBytes(StandardCharsets.UTF_8);
        log.info("keyBytes : {}", keyBytes);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    /**
     * Token 생성 메소드
     *
     * @param String       loginId
     * @param List<String> roles
     * @param long         expiredTime
     * @return Jwt.builder()..
     */
    public String createToken(String loginId, List<String> roles, long expiredTime) {
        Claims claims = Jwts.claims().setSubject(loginId);
        claims.put("roles", roles);
        Date date = new Date();

        Key secretKey = getSecretKey();

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(date)
                .setExpiration(new Date(date.getTime() + expiredTime))
                .signWith(secretKey, SignatureAlgorithm.HS512)
                .compact();
    }

    /*
     * ACCESS_TOKEN 생성
     */
    public String createAccessToken(String loginId, List<String> roles) {
        return createToken(loginId, roles, accessTokenExpiredTime);
    }

    /*
     * REFRESH_TOKEN 생성
     */
    public String createRefreshToken(String loginId, List<String> roles) {
        return createToken(loginId, roles, refreshTokenExpiredTime);
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(getSecretKey())
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException e) {
            log.error("Expired JWT Token {}", e.getMessage());
        }
        return false;
    }

    public String extractLoginId(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSecretKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public Date extractExpiredTime(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSecretKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getExpiration();
    }
}
