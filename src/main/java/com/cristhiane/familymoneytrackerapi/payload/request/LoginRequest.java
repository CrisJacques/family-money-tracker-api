package com.cristhiane.familymoneytrackerapi.payload.request;

import javax.validation.constraints.NotBlank;

/**
 * Classe que constrói um objeto para ser usado na requisição de login
 *
 */
public class LoginRequest {
	@NotBlank
	private String email;

	@NotBlank
	private String password;

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
	 * Getter do parâmetro password
	 * 
	 * @return Senha do usuário
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Setter do parâmetro password
	 * 
	 * @param password - Senha do usuário
	 */
	public void setPassword(String password) {
		this.password = password;
	}
}
