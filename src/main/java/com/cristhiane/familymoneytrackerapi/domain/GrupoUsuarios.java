package com.cristhiane.familymoneytrackerapi.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Classe que representa a entity Grupo de usuários
 *
 */
@Entity
public class GrupoUsuarios implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	private String identificador;

	@JsonIgnore
	@OneToMany(mappedBy = "grupoUsuarios")
	private List<User> admins_grupo = new ArrayList<>();

	/**
	 * Construtor vazio
	 */
	public GrupoUsuarios() {

	}

	/**
	 * Construtor com parâmetros
	 * 
	 * @param id            - Id do grupo de usuários
	 * @param nome          - Nome do grupo de usuários
	 * @param identificador - Identificador do grupo de usuários
	 */
	public GrupoUsuarios(Integer id, String nome, String identificador) {
		super();
		this.id = id;
		this.nome = nome;
		this.identificador = identificador;
	}

	/**
	 * Getter do parâmetro id
	 * 
	 * @return Id do grupo de usuários
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * Setter do parâmetro id
	 * 
	 * @param id - Id do grupo de usuários
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * Getter do parâmetro nome
	 * 
	 * @return Nome do grupo de usuários
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Setter do parâmetro nome
	 * 
	 * @param nome - Nome do grupo de usuários
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * Getter do parâmetro identificador
	 * 
	 * @return Identificador do grupo de usuários
	 */
	public String getIdentificador() {
		return identificador;
	}

	/**
	 * Setter do parâmetro identificador
	 * 
	 * @param identificador - Identificador do grupo de usuários
	 */
	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}

	/**
	 * Getter do parâmetro admins_grupo
	 * 
	 * @return Lista dos usuários que são administradores do grupo
	 */
	public List<User> getAdmins_grupo() {
		return admins_grupo;
	}

	/**
	 * Setter do parâmetro admins_grupo
	 * 
	 * @param admins_grupo - Lista dos usuários que são administradores do grupo
	 */
	public void setAdmins_grupo(List<User> admins_grupo) {
		this.admins_grupo = admins_grupo;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GrupoUsuarios other = (GrupoUsuarios) obj;
		return Objects.equals(id, other.id);
	}

}
