package com.alessio.springdb;

import com.alessio.springdb.constants.Constants;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class JwtUtil {

	private final String KEY = Constants.getKey();

	public String generateToken(UserDetails userDetails) {
		return createToken(userDetails.getUsername());
	}

	private String createToken(String sub) {
		Map<String, Object> claims = new HashMap<>();

		return Jwts.builder().setClaims(claims).setSubject(sub)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + (1000 * 60 * 60 * 2)))		// Last number stands for how many hours the token will last
				.signWith(SignatureAlgorithm.HS256, KEY).compact();
	}

	public Boolean validateToken(String token, UserDetails userDetails) {
		final String username = extractUsername(token);
		return (username.equals(userDetails.getUsername()) && !tokenExpired(token));
	}

	private Boolean tokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}

	public String extractUsername(String token) {
		return extractClaim(token, Claims::getSubject);
	}

	public Date extractExpiration(String token) {
		return extractClaim(token, Claims::getExpiration);
	}

	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
	}

	private Claims extractAllClaims(String token) {
		return Jwts.parser().setSigningKey(KEY).parseClaimsJws(token).getBody();
	}
}
