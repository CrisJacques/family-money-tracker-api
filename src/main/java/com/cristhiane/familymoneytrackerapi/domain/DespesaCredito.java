package com.cristhiane.familymoneytrackerapi.domain;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.cristhiane.familymoneytrackerapi.enums.FormaDePagamento;

@Entity
public class DespesaCredito extends Despesa {

	private static final long serialVersionUID = 1L;

	private Integer numeroParcelas;
	
	@ManyToOne
	@JoinColumn(name = "id_cartao_credito")
	private CartaoDeCredito cartaoDeCredito;
	
	public DespesaCredito() {
		
	}

	public DespesaCredito(Integer id, float valor, String descricao, LocalDate data, boolean recorrente,
			Integer diaLancamentoRecorrente, FormaDePagamento formaDePagamento, CategoriaDespesa categoriaDespesa, User user, Integer numeroParcelas, CartaoDeCredito cartaoDeCredito) {
		super(id, valor, descricao, data, recorrente, diaLancamentoRecorrente, formaDePagamento, categoriaDespesa, user);
		this.numeroParcelas = numeroParcelas;
		this.cartaoDeCredito = cartaoDeCredito;
	}

	public Integer getNumeroParcelas() {
		return numeroParcelas;
	}

	public void setNumeroParcelas(Integer numeroParcelas) {
		this.numeroParcelas = numeroParcelas;
	}

	public CartaoDeCredito getCartaoDeCredito() {
		return cartaoDeCredito;
	}

	public void setCartaoDeCredito(CartaoDeCredito cartaoDeCredito) {
		this.cartaoDeCredito = cartaoDeCredito;
	}

}
