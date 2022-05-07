package com.cristhiane.familymoneytrackerapi.domain;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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
	
	public Receita() {
		
	}
	
	public Receita(Integer id, float valor, String descricao, LocalDate data, boolean recorrente,
			Integer diaLancamentoRecorrente, Conta conta, CategoriaReceita categoriaReceita, User user) {
		super(id, valor, descricao, data, recorrente, diaLancamentoRecorrente);
		this.conta = conta;
		this.categoriaReceita = categoriaReceita;
		this.user = user;
	}
	
	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

	public CategoriaReceita getCategoriaReceita() {
		return categoriaReceita;
	}

	public void setCategoriaReceita(CategoriaReceita categoriaReceita) {
		this.categoriaReceita = categoriaReceita;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}
