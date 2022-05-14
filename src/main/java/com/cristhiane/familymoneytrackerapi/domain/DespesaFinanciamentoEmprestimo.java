package com.cristhiane.familymoneytrackerapi.domain;

import java.time.LocalDate;

import javax.persistence.Entity;

import com.cristhiane.familymoneytrackerapi.enums.Banco;
import com.cristhiane.familymoneytrackerapi.enums.FormaDePagamento;

/**
 * Classe que representa a entity Despesa cuja forma de pagamento é
 * financiamento ou empréstimo
 *
 */
@Entity
public class DespesaFinanciamentoEmprestimo extends Despesa {

	private static final long serialVersionUID = 1L;

	private Integer numeroParcelas;
	private Banco banco;

	/**
	 * Construtor vazio
	 */
	public DespesaFinanciamentoEmprestimo() {

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
	 * @param banco                   - Banco onde foi feito o financiamento ou
	 *                                empréstimo
	 */
	public DespesaFinanciamentoEmprestimo(Integer id, float valor, String descricao, LocalDate data, boolean recorrente,
			Integer diaLancamentoRecorrente, FormaDePagamento formaDePagamento, CategoriaDespesa categoriaDespesa,
			User user, Integer numeroParcelas, Banco banco) {
		super(id, valor, descricao, data, recorrente, diaLancamentoRecorrente, formaDePagamento, categoriaDespesa,
				user);
		this.numeroParcelas = numeroParcelas;
		this.banco = banco;
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
	 * Getter do parâmetro banco
	 * 
	 * @return Banco onde foi feito o financiamento ou empréstimo
	 */
	public Banco getBanco() {
		return banco;
	}

	/**
	 * Setter do parâmetro banco
	 * 
	 * @param banco - Banco onde foi feito o financiamento ou empréstimo
	 */
	public void setBanco(Banco banco) {
		this.banco = banco;
	}

}
