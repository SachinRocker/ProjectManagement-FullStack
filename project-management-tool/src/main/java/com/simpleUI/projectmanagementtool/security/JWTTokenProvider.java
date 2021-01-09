package com.simpleUI.projectmanagementtool.security;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.simpleUI.projectmanagementtool.domain.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.impl.JwtMap;

@Component
public class JWTTokenProvider {

	// generate the token
	public String generateToken(Authentication authetication) {

		User user = (User) authetication.getPrincipal(); // get the user object after authentication
		Date now = new Date();
		Date expire = new Date(now.getTime() + SecurityConstants.EXPIRATION_TIME);

		String userId = Long.toString(user.getId());

		Map<String, Object> claims = new HashMap<String, Object>();
		claims.put("username", user.getUsername());
		claims.put("id", userId);
		claims.put("fullname", user.getFullname());

		return Jwts.builder().setSubject(userId).setClaims(claims).setIssuedAt(now).setExpiration(expire)
				.signWith(SignatureAlgorithm.HS512, SecurityConstants.SECRET).compact();

	}

	// validate the token
	public boolean validateToken(String token) {
		try {
			//parses and validate the token based on the secret key
			Jwts.parser().setSigningKey(SecurityConstants.SECRET).parseClaimsJws(token);
			return true;
			
		} catch (MalformedJwtException m) {
			System.out.println("invalid JWT token");
		} catch (SignatureException s) {
			System.out.println("invalid JWT signature");

		} catch (ExpiredJwtException e) {
			System.out.println("Expired JWT token");
		} catch (UnsupportedJwtException u) {
			System.out.println("Unsupported JWT token");
		} catch (IllegalArgumentException i) {
			System.out.println("JWT claims string is empty");
		}
		return false;
	}
	
	//get user id from token
	public Long getUserIdfromToken(String token) {
		Claims claims = Jwts.parser().setSigningKey(SecurityConstants.SECRET).parseClaimsJws(token).getBody();
		String id = (String) claims.get("id");
		
		return Long.parseLong(id);
	}
	
	

}
