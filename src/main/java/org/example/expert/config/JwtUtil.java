package org.example.expert.config;

import java.util.Base64;
import java.util.Date;

import javax.crypto.SecretKey;

import org.example.expert.domain.common.exception.ServerException;
import org.example.expert.domain.user.enums.UserRole;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "JwtUtil")
@Component
public class JwtUtil {

	private static final String BEARER_PREFIX = "Bearer ";

	private final SecretKey key;

	@Value("${jwt.expiration}")
	private long expiration;

	private final SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

	public JwtUtil(@Value("${secret-key}") String base64Key) {
		this.key = Keys.hmacShaKeyFor(Base64.getDecoder().decode(base64Key));
	}

	public String createToken(Long userId, String email, UserRole userRole, String nickName) {
		Date date = new Date();
		return Jwts.builder()
			.setSubject(String.valueOf(userId))
			.claim("email", email)
			.claim("userRole", userRole)
			.claim("nickName", nickName)
			.setExpiration(new Date(date.getTime() + expiration))
			.setIssuedAt(date) // 발급일
			.signWith(key, signatureAlgorithm) // 암호화 알고리즘
			.compact();
	}

	public String substringToken(String tokenValue) {
		if (StringUtils.hasText(tokenValue) && tokenValue.startsWith(BEARER_PREFIX)) {
			return tokenValue.substring(7);
		}
		throw new ServerException("Not Found Token");
	}

	public Claims extractClaims(String token) {
		return Jwts.parserBuilder()
			.setSigningKey(key)
			.build()
			.parseClaimsJws(token)
			.getBody();
	}

	public boolean validateToken(String token) {
		try {
			return !extractClaims(token).getExpiration().before(new Date());
		} catch (Exception e) {
			return false;
		}
	}

	public Long extractUserId(String token) {
		return Long.parseLong(extractClaims(token).getSubject());
	}

	public String extractEmail(String token) {
		return extractClaims(token).get("email", String.class);
	}

	public String extractRole(String token) {
		return extractClaims(token).get("role", String.class);
	}

	public String extractNickName(String token) {
		return extractClaims(token).get("nickName", String.class);
	}

}
