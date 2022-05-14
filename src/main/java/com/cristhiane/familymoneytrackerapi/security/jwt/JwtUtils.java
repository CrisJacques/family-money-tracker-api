package com.cristhiane.familymoneytrackerapi.security.jwt;

import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import com.cristhiane.familymoneytrackerapi.security.services.UserDetailsImpl;
import io.jsonwebtoken.*;

/**
 * Classe com métodos auxiliares para lidar com tokens JWT
 *
 */
@Component
public class JwtUtils {
	private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);
	@Value("${family-money-tracker-api.app.jwtSecret}")
	private String jwtSecret;
	@Value("${family-money-tracker-api.app.jwtExpirationMs}")
	private int jwtExpirationMs;

	/**
	 * Gera token JWT
	 * 
	 * @param authentication
	 * @return Token JWT
	 */
	public String generateJwtToken(Authentication authentication) {
		UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();
		return Jwts.builder().setSubject((userPrincipal.getEmail())).setIssuedAt(new Date())
				.setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
				.signWith(SignatureAlgorithm.HS512, jwtSecret).compact();
	}

	/**
	 * Obtém o email a partir de um token JWT
	 * 
	 * @param token - Token JWT
	 * @return E-mail do usuário cujo token foi passado por parâmetro
	 */
	public String getEmailFromJwtToken(String token) {
		return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
	}

	/**
	 * Valida o token JWT
	 * 
	 * @param authToken - Token JWT a ser validado
	 * @return Mensagem de erro caso validação não ocorra com sucesso e true caso
	 *         ela ocorra com sucesso
	 */
	public boolean validateJwtToken(String authToken) {
		try {
			Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
			return true;
		} catch (SignatureException e) {
			logger.error("Invalid JWT signature: {}", e.getMessage());
		} catch (MalformedJwtException e) {
			logger.error("Invalid JWT token: {}", e.getMessage());
		} catch (ExpiredJwtException e) {
			logger.error("JWT token is expired: {}", e.getMessage());
		} catch (UnsupportedJwtException e) {
			logger.error("JWT token is unsupported: {}", e.getMessage());
		} catch (IllegalArgumentException e) {
			logger.error("JWT claims string is empty: {}", e.getMessage());
		}
		return false;
	}
}
