package com.cristhiane.familymoneytrackerapi.domain;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.cristhiane.familymoneytrackerapi.enums.FormaDePagamento;

/**
 * Classe que representa a entity Despesa cuja forma de pagamento é cartão de
 * crédito
 *
 */
@Entity
public class DespesaCredito extends Despesa {

	private static final long serialVersionUID = 1L;

	private Integer numeroParcelas;

	@ManyToOne
	@JoinColumn(name = "id_cartao_credito")
	private CartaoDeCredito cartaoDeCredito;

	/**
	 * Construtor vazio
	 */
	public DespesaCredito() {

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
	 * @param numeroParcelas          - Número de parcelas da despesa
	 * @param cartaoDeCredito         - Cartão de crédito utilizado
	 */
	public DespesaCredito(Integer id, float valor, String descricao, LocalDate data, boolean recorrente,
			Integer diaLancamentoRecorrente, FormaDePagamento formaDePagamento, CategoriaDespesa categoriaDespesa,
			User user, Integer numeroParcelas, CartaoDeCredito cartaoDeCredito) {
		super(id, valor, descricao, data, recorrente, diaLancamentoRecorrente, formaDePagamento, categoriaDespesa,
				user);
		this.numeroParcelas = numeroParcelas;
		this.cartaoDeCredito = cartaoDeCredito;
	}

	/**
	 * Getter do parâmetro numeroParcelas
	 * 
	 * @return Número de parcelas da despesa
	 */
	public Integer getNumeroParcelas() {
		return numeroParcelas;
	}

	/**
	 * Setter do parâmetro numeroParcelas
	 * 
	 * @param numeroParcelas - Número de parcelas da despesa
	 */
	public void setNumeroParcelas(Integer numeroParcelas) {
		this.numeroParcelas = numeroParcelas;
	}

	/**
	 * Getter do parâmetro cartaoDeCredito
	 * 
	 * @return Cartão de crédito utilizado
	 */
	public CartaoDeCredito getCartaoDeCredito() {
		return cartaoDeCredito;
	}

	/**
	 * Setter do parâmetro cartaoDeCredito
	 * 
	 * @param cartaoDeCredito - Cartão de crédito utilizado
	 */
	public void setCartaoDeCredito(CartaoDeCredito cartaoDeCredito) {
		this.cartaoDeCredito = cartaoDeCredito;
	}

}
