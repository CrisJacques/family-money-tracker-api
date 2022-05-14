package com.cristhiane.familymoneytrackerapi.domain;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * Classe que representa a entity Receita
 *
 */
@Entity
public class Receita extends Transacao {

	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "id_conta")
	private Conta conta;

	@ManyToOne
	@JoinColumn(name = "id_categoria_receita")
	private CategoriaReceita categoriaReceita;

	@ManyToOne
	@JoinColumn(name = "id_usuario")
	private User user;

	/**
	 * Construtor vazio
	 */
	public Receita() {

	}

	/**
	 * Construtor com parâmetros
	 * 
	 * @param id                      - Id da receita
	 * @param valor                   - Valor da receita
	 * @param descricao               - Descrição da receita
	 * @param data                    - Data da receita
	 * @param recorrente              - Indica se receita se repete todo mês ou não
	 * @param diaLancamentoRecorrente - Dia que receita deve ser lançada todo mês
	 *                                caso ela seja recorrente
	 * @param conta                   - Conta vinculada à receita
	 * @param categoriaReceita        - Categoria da receita
	 * @param user                    - Usuário que cadastrou a receita
	 */
	public Receita(Integer id, float valor, String descricao, LocalDate data, boolean recorrente,
			Integer diaLancamentoRecorrente, Conta conta, CategoriaReceita categoriaReceita, User user) {
		super(id, valor, descricao, data, recorrente, diaLancamentoRecorrente);
		this.conta = conta;
		this.categoriaReceita = categoriaReceita;
		this.user = user;
	}

	/**
	 * Getter do parâmetro conta
	 * 
	 * @return Conta vinculada à receita
	 */
	public Conta getConta() {
		return conta;
	}

	/**
	 * Setter do parâmetro conta
	 * 
	 * @param conta - Conta vinculada à receita
	 */
	public void setConta(Conta conta) {
		this.conta = conta;
	}

	/**
	 * Getter do parâmetro categoriaReceita
	 * 
	 * @return Categoria da receita
	 */
	public CategoriaReceita getCategoriaReceita() {
		return categoriaReceita;
	}

	/**
	 * Setter do parâmetro categoriaReceita
	 * 
	 * @param categoriaReceita - Categoria da receita
	 */
	public void setCategoriaReceita(CategoriaReceita categoriaReceita) {
		this.categoriaReceita = categoriaReceita;
	}

	/**
	 * Getter do parâmetro user
	 * 
	 * @return Usuário que cadastrou a receita
	 */
	public User getUser() {
		return user;
	}

	/**
	 * Setter do parâmetro user
	 * 
	 * @param user - Usuário que cadastrou a receita
	 */
	public void setUser(User user) {
		this.user = user;
	}

}
