package com.cristhiane.familymoneytrackerapi.domain;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.cristhiane.familymoneytrackerapi.enums.FormaDePagamento;

/**
 * Classe que representa a entity Despesa cuja forma de pagamento é débito ou
 * dinheiro
 *
 */
@Entity
public class DespesaDebitoDinheiro extends Despesa {

	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "id_conta")
	private Conta conta;

	/**
	 * Construtor vazio
	 */
	public DespesaDebitoDinheiro() {

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
	 * @param conta                   - Conta vinculada à despesa
	 */
	public DespesaDebitoDinheiro(Integer id, float valor, String descricao, LocalDate data, boolean recorrente,
			Integer diaLancamentoRecorrente, FormaDePagamento formaDePagamento, CategoriaDespesa categoriaDespesa,
			User user, Conta conta) {
		super(id, valor, descricao, data, recorrente, diaLancamentoRecorrente, formaDePagamento, categoriaDespesa,
				user);
		this.conta = conta;

	}

	/**
	 * Getter do parâmetro conta
	 * 
	 * @return Conta vinculada à despesa
	 */
	public Conta getConta() {
		return conta;
	}

	/**
	 * Setter do parâmetro conta
	 * 
	 * @param conta - Conta vinculada à despesa
	 */
	public void setConta(Conta conta) {
		this.conta = conta;
	}

}
