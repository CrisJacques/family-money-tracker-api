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

/**
 * Classe que representa a entity Conta
 *
 */
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

	/**
	 * Construtor vazio
	 */
	public Conta() {

	}

	/**
	 * Construtor com parâmetros
	 * 
	 * @param id         - Id da conta
	 * @param nome       - Nome da conta
	 * @param saldoAtual - Saldo atual da conta
	 * @param tipoConta  - Tipo de conta
	 * @param user       - Usuário que cadastrou a conta
	 */
	public Conta(Integer id, String nome, float saldoAtual, TipoConta tipoConta, User user) {
		super();
		this.id = id;
		this.nome = nome;
		this.saldoAtual = saldoAtual;
		this.tipoConta = tipoConta;
		this.user = user;
	}

	/**
	 * Getter do parâmetro id
	 * 
	 * @return Id da conta
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * Setter do parâmetro id
	 * 
	 * @param id - Id da conta
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * Getter do parâmetro nome
	 * 
	 * @return Nome da conta
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Setter do parâmetro nome
	 * 
	 * @param nome - Nome da conta
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * Getter do parâmetro saldoAtual
	 * 
	 * @return Saldo atual da conta
	 */
	public float getSaldoAtual() {
		return saldoAtual;
	}

	/**
	 * Setter do parâmetro saldoAtual
	 * 
	 * @param saldoAtual - Saldo atual da conta
	 */
	public void setSaldoAtual(float saldoAtual) {
		this.saldoAtual = saldoAtual;
	}

	/**
	 * Getter do parâmetro tipoConta
	 * 
	 * @return Tipo da conta
	 */
	public TipoConta getTipoConta() {
		return tipoConta;
	}

	/**
	 * Setter do parâmetro tipoConta
	 * 
	 * @param tipoConta - Tipo da conta
	 */
	public void setTipoConta(TipoConta tipoConta) {
		this.tipoConta = tipoConta;
	}

	/**
	 * Getter do parâmetro receitas
	 * 
	 * @return Lista das receitas vinculadas à conta
	 */
	public List<Receita> getReceitas() {
		return receitas;
	}

	/**
	 * Setter do parâmetro receitas
	 * 
	 * @param receitas - Lista de receitas vinculadas à conta
	 */
	public void setReceitas(List<Receita> receitas) {
		this.receitas = receitas;
	}

	/**
	 * Getter do parâmetro user
	 * 
	 * @return Usuário que cadastrou a conta
	 */
	public User getUser() {
		return user;
	}

	/**
	 * Setter do parâmetro user
	 * 
	 * @param user - Usuário que cadastrou a conta
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * Getter do parâmetro despesasDebitoDinheiro
	 * 
	 * @return Lista das despesas pagas em dinheiro ou débito vinculadas à conta
	 */
	public List<DespesaDebitoDinheiro> getDespesasDebitoDinheiro() {
		return despesasDebitoDinheiro;
	}

	/**
	 * Setter do parâmetro despesasDebitoDinheiro
	 * 
	 * @param despesasDebitoDinheiro - Lista das despesas pagas em dinheiro ou
	 *                               débito vinculadas à conta
	 */
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
