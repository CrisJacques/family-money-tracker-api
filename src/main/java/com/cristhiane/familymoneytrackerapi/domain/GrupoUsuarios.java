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
	
	public GrupoUsuarios() {
		
	}

	public GrupoUsuarios(Integer id, String nome, String identificador) {
		super();
		this.id = id;
		this.nome = nome;
		this.identificador = identificador;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getIdentificador() {
		return identificador;
	}

	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}

	public List<User> getAdmins_grupo() {
		return admins_grupo;
	}

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
