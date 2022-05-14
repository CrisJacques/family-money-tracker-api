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

/**
 * Classe que representa a entity Usuário
 *
 */
@Entity
@Table(name = "users", uniqueConstraints = { @UniqueConstraint(columnNames = "username"),
		@UniqueConstraint(columnNames = "email") })
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
	@JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
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

	/**
	 * Construtor vazio
	 */
	public User() {
	}

	/**
	 * Construtor com parâmetros
	 * 
	 * @param username - Nome do usuário
	 * @param email    - E-mail do usuário
	 * @param password - Senha do usuário
	 */
	public User(String username, String email, String password) {
		this.username = username;
		this.email = email;
		this.password = password;
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
	 * @param username
	 */
	public void setUsername(String username) {
		this.username = username;
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

	/**
	 * Getter do parâmetro roles
	 * 
	 * @return Perfis aos quais o usuário pertence
	 */
	public Set<Role> getRoles() {
		return roles;
	}

	/**
	 * Setter do parâmetro roles
	 * 
	 * @param roles - Perfis aos quais o usuário pertence
	 */
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	/**
	 * Getter do parâmetro receitas
	 * 
	 * @return Lista de receitas cadastradas pelo usuário
	 */
	public List<Receita> getReceitas() {
		return receitas;
	}

	/**
	 * Setter do parâmetro receitas
	 * 
	 * @param receitas - Lista de receitas cadastradas pelo usuário
	 */
	public void setReceitas(List<Receita> receitas) {
		this.receitas = receitas;
	}

	/**
	 * Getter do parâmetro categoriasDespesa
	 * 
	 * @return Categorias de despesa cadastradas pelo usuário
	 */
	public List<CategoriaDespesa> getCategoriasDespesa() {
		return categoriasDespesa;
	}

	/**
	 * Setter do parâmetro categoriasDespesa
	 * 
	 * @param categoriasDespesa - Categorias de despesa cadastradas pelo usuário
	 */
	public void setCategoriasDespesa(List<CategoriaDespesa> categoriasDespesa) {
		this.categoriasDespesa = categoriasDespesa;
	}

	/**
	 * Getter do parâmetro categoriasReceita
	 * 
	 * @return Categorias de receita cadastradas pelo usuário
	 */
	public List<CategoriaReceita> getCategoriasReceita() {
		return categoriasReceita;
	}

	/**
	 * Setter do parâmetro categoriasReceita
	 * 
	 * @param categoriasReceita - Categorias de receita cadastradas pelo usuário
	 */
	public void setCategoriasReceita(List<CategoriaReceita> categoriasReceita) {
		this.categoriasReceita = categoriasReceita;
	}

	/**
	 * Getter do parâmetro contas
	 * 
	 * @return Contas cadastradas pelo usuário
	 */
	public List<Conta> getContas() {
		return contas;
	}

	/**
	 * Setter do parâmetro contas
	 * 
	 * @param contas - Contas cadastradas pelo usuário
	 */
	public void setContas(List<Conta> contas) {
		this.contas = contas;
	}

	/**
	 * Getter do parâmetro cartoesDeCredito
	 * 
	 * @return Lista de cartões de crédito cadastrados pelo usuário
	 */
	public List<CartaoDeCredito> getCartoesDeCredito() {
		return cartoesDeCredito;
	}

	/**
	 * Setter do parâmetro cartoesDeCredito
	 * 
	 * @param cartoesDeCredito - Lista de cartões de crédito cadastrados pelo
	 *                         usuário
	 */
	public void setCartoesDeCredito(List<CartaoDeCredito> cartoesDeCredito) {
		this.cartoesDeCredito = cartoesDeCredito;
	}

	/**
	 * Getter do parâmetro despesas
	 * 
	 * @return Lista de despesas cadastradas pelo usuário
	 */
	public List<Despesa> getDespesas() {
		return despesas;
	}

	/**
	 * Setter do parâmetro despesas
	 * 
	 * @param despesas - Lista de despesas cadastradas pelo usuário
	 */
	public void setDespesas(List<Despesa> despesas) {
		this.despesas = despesas;
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