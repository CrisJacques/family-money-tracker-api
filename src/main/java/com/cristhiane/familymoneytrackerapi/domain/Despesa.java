package com.cristhiane.familymoneytrackerapi.domain;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.cristhiane.familymoneytrackerapi.enums.FormaDePagamento;

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
	
	public Despesa() {
		
	}

	public Despesa(Integer id, float valor, String descricao, LocalDate data, boolean recorrente,
			Integer diaLancamentoRecorrente, FormaDePagamento formaDePagamento, CategoriaDespesa categoriaDespesa, User user) {
		super(id, valor, descricao, data, recorrente, diaLancamentoRecorrente);
		this.formaDePagamento = formaDePagamento;
		this.categoriaDespesa = categoriaDespesa;
		this.user = user;
	}

	public FormaDePagamento getFormaDePagamento() {
		return formaDePagamento;
	}

	public void setFormaDePagamento(FormaDePagamento formaDePagamento) {
		this.formaDePagamento = formaDePagamento;
	}

	public CategoriaDespesa getCategoriaDespesa() {
		return categoriaDespesa;
	}

	public void setCategoriaDespesa(CategoriaDespesa categoriaDespesa) {
		this.categoriaDespesa = categoriaDespesa;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
