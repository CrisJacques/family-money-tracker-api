package com.cristhiane.familymoneytrackerapi.domain;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.cristhiane.familymoneytrackerapi.enums.FormaDePagamento;

@Entity
public class DespesaDebitoDinheiro extends Despesa {

	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	@JoinColumn(name = "id_conta")
	private Conta conta;

	public DespesaDebitoDinheiro() {
		
	}
	
	public DespesaDebitoDinheiro(Integer id, float valor, String descricao, LocalDate data, boolean recorrente,
			Integer diaLancamentoRecorrente, FormaDePagamento formaDePagamento, CategoriaDespesa categoriaDespesa, User user, Conta conta) {
		super(id, valor, descricao, data, recorrente, diaLancamentoRecorrente, formaDePagamento, categoriaDespesa, user);
		this.conta = conta;

	}

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

}
