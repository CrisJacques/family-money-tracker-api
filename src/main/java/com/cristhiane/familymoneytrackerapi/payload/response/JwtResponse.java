package com.cristhiane.familymoneytrackerapi.payload.response;

import java.util.List;

import com.cristhiane.familymoneytrackerapi.domain.GrupoUsuarios;

/**
 * Classe que constrói um objeto para ser retornado na resposta da requisição de
 * login
 *
 */
public class JwtResponse {
	private String token;
	private String type = "Bearer";
	private Long id;
	private String username;
	private String email;
	private List<String> roles;
	private GrupoUsuarios grupoUsuarios;

	/**
	 * Construtor
	 * 
	 * @param accessToken   - Token do usuário
	 * @param id            - Id do usuário
	 * @param username      - Nome do usuário
	 * @param email         - E-mail do usuário
	 * @param roles         - Perfis do usuário
	 * @param grupoUsuarios - Grupos aos quais o usuário pertence
	 */
	public JwtResponse(String accessToken, Long id, String username, String email, List<String> roles,
			GrupoUsuarios grupoUsuarios) {
		this.token = accessToken;
		this.id = id;
		this.username = username;
		this.email = email;
		this.roles = roles;
		this.grupoUsuarios = grupoUsuarios;
	}

	/**
	 * Getter do parâmetro token
	 * 
	 * @return Token do usuário
	 */
	public String getAccessToken() {
		return token;
	}

	/**
	 * Setter do parâmetro token
	 * 
	 * @param accessToken - Token do usuário
	 */
	public void setAccessToken(String accessToken) {
		this.token = accessToken;
	}

	/**
	 * Getter do parâmetro type
	 * 
	 * @return Tipo do token
	 */
	public String getTokenType() {
		return type;
	}

	/**
	 * Setter do parâmetro type
	 * 
	 * @param tokenType - Tipo do token
	 */
	public void setTokenType(String tokenType) {
		this.type = tokenType;
	}

	/**
	 * Getter do parâmetro id
	 * 
	 * @return Id do usuário
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Setter do parâmetro id
	 * 
	 * @param id - Id do usuário
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Getter do parâmetro email
	 * 
	 * @return E-mail do usuário
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Setter do parâmetro email
	 * 
	 * @param email - E-mail do usuário
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Getter do parâmetro username
	 * 
	 * @return Nome do usuário
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Setter do parâmetro username
	 * 
	 * @param username - Nome do usuário
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * Getter do parâmetro roles
	 * 
	 * @return Perfis do usuário
	 */
	public List<String> getRoles() {
		return roles;
	}

	/**
	 * Getter do parâmetro grupoUsuarios
	 * 
	 * @return Grupo de usuários ao qual o usuário pertence
	 */
	public GrupoUsuarios getGrupoUsuarios() {
		return grupoUsuarios;
	}

	/**
	 * Setter do parâmetro grupoUsuarios
	 * 
	 * @param grupoUsuarios - Grupo de usuários ao qual o usuário pertence
	 */
	public void setGrupoUsuarios(GrupoUsuarios grupoUsuarios) {
		this.grupoUsuarios = grupoUsuarios;
	}

}
