package ua.logos.config.jwt;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import ua.logos.config.constants.SecurityConstants;
import ua.logos.entity.UserRole;

@Component
public class JWTTokenProvider {
	@Autowired
	private UserDetailsService userDetailsService;
	
	public String createToken(String username, UserRole role) {
		Claims claims = Jwts.claims().setSubject(username);
		claims.put("auth", AuthorityUtils.createAuthorityList(String.valueOf(role)));
		
		Date now = new Date();
		
		Date validity = new Date(now.getTime() + SecurityConstants.EXPIRATION_TIME);
		return Jwts.builder()
				.setClaims(claims)
				.setIssuedAt(now)
				.setExpiration(validity)
				.signWith(SignatureAlgorithm.HS256, SecurityConstants.TOKEN_SECRET)
				.compact();
	}
	public boolean validateToken(String token) {
		try {
			Jwts.parser().setSigningKey(SecurityConstants.TOKEN_SECRET).parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	public String resolveToken(HttpServletRequest req) {
		String bearerToken = req.getHeader(SecurityConstants.HEADER_STRING);
		
		if(bearerToken != null && bearerToken.startsWith(SecurityConstants.TOKEN_PREFIX)) {
			return bearerToken.substring(7, bearerToken.length());
			
		}
		return null;
	}
	
	public String getUsername(String token) {
		return Jwts.parser()
				.setSigningKey(SecurityConstants.TOKEN_SECRET)
				.parseClaimsJws(token).getBody().getSubject();
	}
	
	public Authentication getAuthentication(String token) {
		UserDetails userDetails = userDetailsService.loadUserByUsername(getUsername(token));
		
		return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
	}
	
}
