package com.apexmotion.security;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.apexmotion.entity.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
@Component // Spring quản lý class này, có thể inject vào nơi khác
public class JwtTokenProvider {
	private final SecretKey key;
	private final long accessTokenExpiration;
	private final long refreshTokenExpiration;
	public JwtTokenProvider (
			@Value("${jwt.secret}") String secret,
			@Value("${jwt.access-token-expiration}") long accessTokenExpiration,
			@Value("${jwt.refresh-token-expiration}") long refreshTokenExpiration
			) {
		this.key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
		this.accessTokenExpiration = accessTokenExpiration;
		this.refreshTokenExpiration = refreshTokenExpiration;
	}
	public String generateAccessToken (User user) {
		Date now = new Date();
		Date expiry = new Date (now.getTime() + accessTokenExpiration);
		return Jwts.builder()
				.subject(String.valueOf(user.getId()))
				.claim("email", user.getEmail())
				.claim("role", user.getRole())
				.issuedAt(now)
				.expiration(expiry)
				.signWith(key)
				.compact();
	}
	public String generateRefreshToken (User user) {
		Date now = new Date();
		Date expiry = new Date(now.getTime() + refreshTokenExpiration);
		return Jwts.builder()
				.subject(String.valueOf(user.getId()))
				.issuedAt(now)
				.expiration(expiry)
				.signWith(key)
				.compact();
	}
	public long getUserIdFromToken (String token) {
		Claims claim = Jwts.parser()
						.verifyWith(key)
						.build()
						.parseSignedClaims(token)
						.getPayload();
		return Long.parseLong(claim.getSubject());
	}
	public boolean validateToken (String token) {
		try {
			Jwts.parser()
			.verifyWith(key)
			.build()
			.parseSignedClaims(token);
		return true;
		} catch (JwtException | IllegalArgumentException e) {
			return false;
		}
	}
}
