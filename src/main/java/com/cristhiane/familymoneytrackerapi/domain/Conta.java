package com.cristhiane.familymoneytrackerapi.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.cristhiane.familymoneytrackerapi.enums.TipoConta;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Conta implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	private float saldoAtual;
	private TipoConta tipoConta;
	
	@JsonIgnore
	@OneToMany(mappedBy = "conta")
	private List<Receita> receitas = new ArrayList<>();
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "id_usuario")
	private User user;
	
	@JsonIgnore
	@OneToMany(mappedBy = "conta")
	private List<DespesaDebitoDinheiro> despesasDebitoDinheiro = new ArrayList<>();
	
	public Conta() {
		
	}

	public Conta(Integer id, String nome, float saldoAtual, TipoConta tipoConta, User user) {
		super();
		this.id = id;
		this.nome = nome;
		this.saldoAtual = saldoAtual;
		this.tipoConta = tipoConta;
		this.user = user;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public float getSaldoAtual() {
		return saldoAtual;
	}

	public void setSaldoAtual(float saldoAtual) {
		this.saldoAtual = saldoAtual;
	}

	public TipoConta getTipoConta() {
		return tipoConta;
	}

	public void setTipoConta(TipoConta tipoConta) {
		this.tipoConta = tipoConta;
	}

	public List<Receita> getReceitas() {
		return receitas;
	}

	public void setReceitas(List<Receita> receitas) {
		this.receitas = receitas;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<DespesaDebitoDinheiro> getDespesasDebitoDinheiro() {
		return despesasDebitoDinheiro;
	}

	public void setDespesasDebitoDinheiro(List<DespesaDebitoDinheiro> despesasDebitoDinheiro) {
		this.despesasDebitoDinheiro = despesasDebitoDinheiro;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Conta other = (Conta) obj;
		return Objects.equals(id, other.id);
	}

}
