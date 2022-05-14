package com.cristhiane.familymoneytrackerapi.domain;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.cristhiane.familymoneytrackerapi.enums.FormaDePagamento;

/**
 * Classe que representa a entity Despesa
 *
 */
@Entity
public abstract class Despesa extends Transacao {

	private static final long serialVersionUID = 1L;

	private FormaDePagamento formaDePagamento;

	@ManyToOne
	@JoinColumn(name = "id_categoria_despesa")
	private CategoriaDespesa categoriaDespesa;

	@ManyToOne
	@JoinColumn(name = "id_usuario")
	private User user;

	/**
	 * Construtor vazio
	 */
	public Despesa() {

	}

	/**
	 * Construtor com parâmetros
	 * 
	 * @param id                      - Id da despesa
	 * @param valor                   - Valor da despesa
	 * @param descricao               - Descrição da despesa
	 * @param data                    - Data da despesa
	 * @param recorrente              - Indica se despesa se repete todo mês ou não
	 * @param diaLancamentoRecorrente - Dia que despesa deve ser lançada todo mês
	 *                                caso ela seja recorrente
	 * @param formaDePagamento        - Forma de pagamento
	 * @param categoriaDespesa        - Categoria da despesa
	 * @param user                    - Usuário que cadastrou a despesa
	 */
	public Despesa(Integer id, float valor, String descricao, LocalDate data, boolean recorrente,
			Integer diaLancamentoRecorrente, FormaDePagamento formaDePagamento, CategoriaDespesa categoriaDespesa,
			User user) {
		super(id, valor, descricao, data, recorrente, diaLancamentoRecorrente);
		this.formaDePagamento = formaDePagamento;
		this.categoriaDespesa = categoriaDespesa;
		this.user = user;
	}

	/**
	 * Getter do parâmetro formaDePagamento
	 * 
	 * @return Forma de pagamento da despesa
	 */
	public FormaDePagamento getFormaDePagamento() {
		return formaDePagamento;
	}

	/**
	 * Setter do parâmetro formaDePagamento
	 * 
	 * @param formaDePagamento - Forma de pagamento da despesa
	 */
	public void setFormaDePagamento(FormaDePagamento formaDePagamento) {
		this.formaDePagamento = formaDePagamento;
	}

	/**
	 * Getter do parâmetro categoriaDespesa
	 * 
	 * @return Categoria da despesa
	 */
	public CategoriaDespesa getCategoriaDespesa() {
		return categoriaDespesa;
	}

	/**
	 * Setter do parâmetro categoriaDespesa
	 * 
	 * @param categoriaDespesa - Categoria da despesa
	 */
	public void setCategoriaDespesa(CategoriaDespesa categoriaDespesa) {
		this.categoriaDespesa = categoriaDespesa;
	}

	/**
	 * Getter do parâmetro user
	 * 
	 * @return Usuário que cadastrou a despesa
	 */
	public User getUser() {
		return user;
	}

	/**
	 * Setter do parâmetro user
	 * 
	 * @param user - Usuário que cadastrou a despesa
	 */
	public void setUser(User user) {
		this.user = user;
	}

}
