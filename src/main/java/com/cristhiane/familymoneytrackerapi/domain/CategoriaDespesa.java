package com.cristhiane.familymoneytrackerapi.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Classe que representa a entity Categoria de despesa
 *
 */
@Entity
public class CategoriaDespesa extends CategoriaTransacao {
	private static final long serialVersionUID = 1L;
	private float limite;

	@ManyToOne
	@JoinColumn(name = "id_usuario")
	private User user;

	@JsonIgnore
	@OneToMany(mappedBy = "categoriaDespesa")
	private List<Despesa> despesas = new ArrayList<>();

	/**
	 * Construtor vazio
	 */
	public CategoriaDespesa() {

	}

	/**
	 * Construtor com parâmetros
	 * 
	 * @param id        - Id da categoria de despesa
	 * @param nome      - Nome da categoria de despesa
	 * @param descricao - Descrição da categoria de despesa
	 * @param limite    - Limite de gastos da categoria de despesa
	 * @param user      - Usuário que cadastrou a categoria de despesa
	 */
	public CategoriaDespesa(Integer id, String nome, String descricao, float limite, User user) {
		super(id, nome, descricao);
		this.limite = limite;
		this.user = user;
	}

	/**
	 * Getter do parâmetro limite
	 * 
	 * @return Valor do limite da categoria de despesa
	 */
	public float getLimite() {
		return limite;
	}

	/**
	 * Setter do parâmetro limite
	 * 
	 * @param limite - Limite da categoria de despesa
	 */
	public void setLimite(float limite) {
		this.limite = limite;
	}

	/**
	 * Getter do parâmetro user
	 * 
	 * @return Usuário que cadastrou a categoria de despesa
	 */
	public User getUser() {
		return user;
	}

	/**
	 * Setter do parâmetro user
	 * 
	 * @param user - Usuário que cadastrou a categoria de despesa
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * Getter do parâmetro despesas
	 * 
	 * @return Lista de despesas relacionadas à categoria
	 */
	public List<Despesa> getDespesas() {
		return despesas;
	}

	/**
	 * Setter do parâmetro despesas
	 * 
	 * @param despesas - Lista de despesas relacionadas à categoria
	 */
	public void setDespesas(List<Despesa> despesas) {
		this.despesas = despesas;
	}

}
