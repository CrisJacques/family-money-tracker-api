package com.cristhiane.familymoneytrackerapi.domain;

import java.time.LocalDate;

import javax.persistence.Entity;

import com.cristhiane.familymoneytrackerapi.enums.Banco;
import com.cristhiane.familymoneytrackerapi.enums.FormaDePagamento;

@Entity
public class DespesaFinanciamentoEmprestimo extends Despesa {

	private static final long serialVersionUID = 1L;
	
	private Integer numeroParcelas;
	private Banco banco;
	
	public DespesaFinanciamentoEmprestimo() {
		
	}

	public DespesaFinanciamentoEmprestimo(Integer id, float valor, String descricao, LocalDate data, boolean recorrente,
			Integer diaLancamentoRecorrente, FormaDePagamento formaDePagamento, CategoriaDespesa categoriaDespesa, User user, Integer numeroParcelas, Banco banco) {
		super(id, valor, descricao, data, recorrente, diaLancamentoRecorrente, formaDePagamento, categoriaDespesa, user);
		this.numeroParcelas = numeroParcelas;
		this.banco = banco;
	}

	public Integer getNumeroParcelas() {
		return numeroParcelas;
	}

	public void setNumeroParcelas(Integer numeroParcelas) {
		this.numeroParcelas = numeroParcelas;
	}

	public Banco getBanco() {
		return banco;
	}

	public void setBanco(Banco banco) {
		this.banco = banco;
	}
	
	
	
	
}
