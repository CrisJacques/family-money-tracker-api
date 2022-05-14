package com.cristhiane.familymoneytrackerapi.domain;

import javax.persistence.*;
import com.cristhiane.familymoneytrackerapi.enums.TipoUsuario;

/**
 * Classe que representa a entity Roles (perfis de usuário)
 *
 */
@Entity
@Table(name = "roles")
public class Role {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Enumerated(EnumType.STRING)
	@Column(length = 20)
	private TipoUsuario name;

	/**
	 * Construtor vazio
	 */
	public Role() {
	}

	/**
	 * Construtor com parâmetros
	 * 
	 * @param name - Nome do perfil de usuário
	 */
	public Role(TipoUsuario name) {
		this.name = name;
	}

	/**
	 * Getter do parâmetro id
	 * 
	 * @return Id do perfil de usuário
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * Setter do parâmetro id
	 * 
	 * @param id - Id do perfil de usuário
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * Getter do parâmetro name
	 * 
	 * @return Nome do perfil de usuário
	 */
	public TipoUsuario getName() {
		return name;
	}

	/**
	 * Setter do parâmetro name
	 * 
	 * @param name - Nome do perfil de usuário
	 */
	public void setName(TipoUsuario name) {
		this.name = name;
	}
}
