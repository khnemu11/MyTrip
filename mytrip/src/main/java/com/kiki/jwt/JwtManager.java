package com.kiki.jwt;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.kiki.user.model.UserDto;

import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtManager {
	private final String securityKey = "";
	private final Long expiredTime = 1000 * 60L * 60L * 3L; // 유효시간 3시간
	
	public String generateJwtToken(UserDto user) {
		Date now = java.sql.Timestamp.valueOf(LocalDateTime.now());
		Date expiryDate = java.sql.Timestamp.valueOf(LocalDateTime.now().plusHours(1));
		return Jwts.builder()
				.setSubject(user.getId())
				.setHeader(createHeader())
				.setClaims(createClaims(user))
				.setIssuedAt(now)
				.setExpiration(expiryDate)
				.signWith(SignatureAlgorithm.HS256, securityKey)
				.compact();
	}

	private Map<String, Object> createHeader() {
		Map<String, Object> header = new HashMap<>();
		header.put("typ", "JWT");
		header.put("alg", "HS256");
		return header;
	}

	private Map<String, Object> createClaims(UserDto user) {
		Map<String, Object> claims = new HashMap<>();
//		claims.put("userInfo", user); //로그인 정보
		return claims;
	}
}
