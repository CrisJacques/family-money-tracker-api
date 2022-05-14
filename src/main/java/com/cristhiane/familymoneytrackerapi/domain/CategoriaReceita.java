package com.cristhiane.familymoneytrackerapi.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Classe que representa a entity Categoria de receita
 *
 */
@Entity
public class CategoriaReceita extends CategoriaTransacao {

	private static final long serialVersionUID = 1L;

	@JsonIgnore
	@OneToMany(mappedBy = "categoriaReceita")
	private List<Receita> receitas = new ArrayList<>();

	@ManyToOne
	@JoinColumn(name = "id_usuario")
	private User user;

	/**
	 * Construtor vazio
	 */
	public CategoriaReceita() {

	}

	/**
	 * Construtor com parâmetros
	 * 
	 * @param id        - Id da categoria de receita
	 * @param nome      - Nome da categoria de receita
	 * @param descricao - Descrição da categoria de receita
	 * @param user      - Usuário que cadastrou a categoria de receita
	 */
	public CategoriaReceita(Integer id, String nome, String descricao, User user) {
		super(id, nome, descricao);
		this.user = user;
	}

	/**
	 * Getter do parâmetro receitas
	 * 
	 * @return - Lista das receitas vinculadas à categoria
	 */
	public List<Receita> getReceitas() {
		return receitas;
	}

	/**
	 * Setter do parâmetro receitas
	 * 
	 * @param receitas - Lista das receitas vinculadas à categoria
	 */
	public void setReceitas(List<Receita> receitas) {
		this.receitas = receitas;
	}

	/**
	 * Getter do parâmetro user
	 * 
	 * @return Usuário que cadastrou a categoria de receita
	 */
	public User getUser() {
		return user;
	}

	/**
	 * Setter do parâmetro user
	 * 
	 * @param user - Usuário que cadastrou a categoria de receita
	 */
	public void setUser(User user) {
		this.user = user;
	}

}
