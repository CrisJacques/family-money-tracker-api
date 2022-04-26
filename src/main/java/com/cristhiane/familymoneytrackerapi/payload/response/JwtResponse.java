package com.cristhiane.familymoneytrackerapi.payload.response;

import java.util.List;

import com.cristhiane.familymoneytrackerapi.domain.GrupoUsuarios;

public class JwtResponse {
	private String token;
	private String type = "Bearer";
	private Long id;
	private String username;
	private String email;
	private List<String> roles;
	private GrupoUsuarios grupoUsuarios;

	public JwtResponse(String accessToken, Long id, String username, String email, List<String> roles,
			GrupoUsuarios grupoUsuarios) {
		this.token = accessToken;
		this.id = id;
		this.username = username;
		this.email = email;
		this.roles = roles;
		this.grupoUsuarios = grupoUsuarios;
	}

	public String getAccessToken() {
		return token;
	}

	public void setAccessToken(String accessToken) {
		this.token = accessToken;
	}

	public String getTokenType() {
		return type;
	}

	public void setTokenType(String tokenType) {
		this.type = tokenType;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<String> getRoles() {
		return roles;
	}

	public GrupoUsuarios getGrupoUsuarios() {
		return grupoUsuarios;
	}

	public void setGrupoUsuarios(GrupoUsuarios grupoUsuarios) {
		this.grupoUsuarios = grupoUsuarios;
	}

}
