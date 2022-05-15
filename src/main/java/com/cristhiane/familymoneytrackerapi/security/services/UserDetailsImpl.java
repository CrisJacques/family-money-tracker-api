package com.cristhiane.familymoneytrackerapi.security.services;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.cristhiane.familymoneytrackerapi.domain.GrupoUsuarios;
import com.cristhiane.familymoneytrackerapi.domain.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Classe que provê informações importantes sobre o usuário
 *
 */
public class UserDetailsImpl implements UserDetails {
	private static final long serialVersionUID = 1L;
	private Long id;
	private String username;
	private String email;
	private GrupoUsuarios grupoUsuarios;
	@JsonIgnore
	private String password;
	private Collection<? extends GrantedAuthority> authorities;

	/**
	 * Construtor
	 * 
	 * @param id            - Id do usuário
	 * @param username      - Nome do usuário
	 * @param email         - E-mail do usuário
	 * @param password      - Senha do usuário
	 * @param authorities   - Tem relação com os perfis do usuário (quais permissões
	 *                      ele tem)
	 * @param grupoUsuarios - Grupo de usuários ao qual o usuário pertence
	 */
	public UserDetailsImpl(Long id, String username, String email, String password,
			Collection<? extends GrantedAuthority> authorities, GrupoUsuarios grupoUsuarios) {
		this.id = id;
		this.username = username;
		this.email = email;
		this.password = password;
		this.authorities = authorities;
		this.grupoUsuarios = grupoUsuarios;
	}

	/**
	 * Lista as informações relevantes do usuário
	 * 
	 * @param user - Usuário cujas informações se deseja obter
	 * @return Informações do usuário
	 */
	public static UserDetailsImpl build(User user) {
		List<GrantedAuthority> authorities = user.getRoles().stream()
				.map(role -> new SimpleGrantedAuthority(role.getName().name())).collect(Collectors.toList());
		return new UserDetailsImpl(user.getId(), user.getUsername(), user.getEmail(), user.getPassword(), authorities,
				user.getGrupoUsuarios());
	}

	/**
	 * Getter do parâmetro authorities
	 */
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
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
	 * Getter do parâmetro email
	 * 
	 * @return E-mail do usuário
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Getter do parâmetro password
	 */
	@Override
	public String getPassword() {
		return password;
	}

	/**
	 * Getter do parâmetro username
	 */
	@Override
	public String getUsername() {
		return username;
	}

	/**
	 * Retorna se conta do usuário é válida (ou seja, não está expirada)
	 */
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	/**
	 * Retorna se conta do usuário está desbloqueada
	 */
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	/**
	 * Retorna se credenciais do usuário são válidas (ou seja, não estão expiradas)
	 */
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	/**
	 * Retorna se conta do usuário está habilitada
	 */
	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		UserDetailsImpl user = (UserDetailsImpl) o;
		return Objects.equals(id, user.id);
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
