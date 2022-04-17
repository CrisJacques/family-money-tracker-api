package com.cristhiane.familymoneytrackerapi.domain;

import javax.persistence.*;

import com.cristhiane.familymoneytrackerapi.enums.TipoUsuario;
@Entity
@Table(name = "roles")
public class Role {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Enumerated(EnumType.STRING)
	@Column(length = 20)
	private TipoUsuario name;
	public Role() {
	}
	public Role(TipoUsuario name) {
		this.name = name;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public TipoUsuario getName() {
		return name;
	}
	public void setName(TipoUsuario name) {
		this.name = name;
	}
}
