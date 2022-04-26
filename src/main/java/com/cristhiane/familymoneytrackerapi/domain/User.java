package com.cristhiane.familymoneytrackerapi.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
@Table(	name = "users", 
		uniqueConstraints = { 
			@UniqueConstraint(columnNames = "username"),
			@UniqueConstraint(columnNames = "email") 
		})
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank
	@Size(max = 20)
	private String username;
	@NotBlank
	@Size(max = 50)
	@Email
	private String email;
	@NotBlank
	@Size(max = 120)
	private String password;
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(	name = "user_roles", 
				joinColumns = @JoinColumn(name = "user_id"), 
				inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();
	
	@JsonIgnore
	@OneToMany(mappedBy = "user")
	private List<Receita> receitas = new ArrayList<>();
	
	@JsonIgnore
	@OneToMany(mappedBy = "user")
	private List<Despesa> despesas = new ArrayList<>();
	
	@JsonIgnore
	@OneToMany(mappedBy = "user")
	private List<CategoriaDespesa> categoriasDespesa = new ArrayList<>();
	
	@JsonIgnore
	@OneToMany(mappedBy = "user")
	private List<CategoriaReceita> categoriasReceita = new ArrayList<>();
	
	@JsonIgnore
	@OneToMany(mappedBy = "user")
	private List<Conta> contas = new ArrayList<>();
	
	@JsonIgnore
	@OneToMany(mappedBy = "user")
	private List<CartaoDeCredito> cartoesDeCredito = new ArrayList<>();
	
	@ManyToOne
	@JoinColumn(name = "id_grupo_usuarios")
	private GrupoUsuarios grupoUsuarios;
	
	public User() {
	}
	
	public User(String username, String email, String password) {
		this.username = username;
		this.email = email;
		this.password = password;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public Set<Role> getRoles() {
		return roles;
	}
	
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
	public List<Receita> getReceitas() {
		return receitas;
	}
	
	public void setReceitas(List<Receita> receitas) {
		this.receitas = receitas;
	}

	public List<CategoriaDespesa> getCategoriasDespesa() {
		return categoriasDespesa;
	}

	public void setCategoriasDespesa(List<CategoriaDespesa> categoriasDespesa) {
		this.categoriasDespesa = categoriasDespesa;
	}

	public List<CategoriaReceita> getCategoriasReceita() {
		return categoriasReceita;
	}

	public void setCategoriasReceita(List<CategoriaReceita> categoriasReceita) {
		this.categoriasReceita = categoriasReceita;
	}

	public List<Conta> getContas() {
		return contas;
	}

	public void setContas(List<Conta> contas) {
		this.contas = contas;
	}

	public List<CartaoDeCredito> getCartoesDeCredito() {
		return cartoesDeCredito;
	}

	public void setCartoesDeCredito(List<CartaoDeCredito> cartoesDeCredito) {
		this.cartoesDeCredito = cartoesDeCredito;
	}

	public List<Despesa> getDespesas() {
		return despesas;
	}

	public void setDespesas(List<Despesa> despesas) {
		this.despesas = despesas;
	}

	public GrupoUsuarios getGrupoUsuarios() {
		return grupoUsuarios;
	}

	public void setGrupoUsuarios(GrupoUsuarios grupoUsuarios) {
		this.grupoUsuarios = grupoUsuarios;
	}

}