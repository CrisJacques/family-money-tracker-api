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

import com.cristhiane.familymoneytrackerapi.enums.BandeiraCartaoDeCredito;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Classe que representa a entity Cartão de crédito
 *
 */
@Entity
public class CartaoDeCredito implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	private BandeiraCartaoDeCredito bandeiraCartaoDeCredito;
	private float limite;
	private Integer diaFechamentoFatura;
	private Integer diaVencimentoFatura;

	@ManyToOne
	@JoinColumn(name = "id_usuario")
	private User user;

	@JsonIgnore
	@OneToMany(mappedBy = "cartaoDeCredito")
	private List<DespesaCredito> despesasCredito = new ArrayList<>();

	/**
	 * Construtor vazio
	 */
	public CartaoDeCredito() {

	}

	/**
	 * Construtor com parâmetros
	 * 
	 * @param id                      - Id do cartão de crédito
	 * @param nome                    - Nome do cartão de crédito
	 * @param bandeiraCartaoDeCredito - Bandeira do cartão de crédito
	 * @param limite                  - Limite do cartão de crédito
	 * @param diaFechamentoFatura     - Dia do mês em que ocorre o fechamento da
	 *                                fatura
	 * @param diaVencimentoFatura     - Dia do mês em que ocorre o vencimento da
	 *                                fatura
	 * @param user                    - Usuário que está cadastrando o cartão de
	 *                                crédito
	 */
	public CartaoDeCredito(Integer id, String nome, BandeiraCartaoDeCredito bandeiraCartaoDeCredito, float limite,
			Integer diaFechamentoFatura, Integer diaVencimentoFatura, User user) {
		super();
		this.id = id;
		this.nome = nome;
		this.bandeiraCartaoDeCredito = bandeiraCartaoDeCredito;
		this.limite = limite;
		this.diaFechamentoFatura = diaFechamentoFatura;
		this.diaVencimentoFatura = diaVencimentoFatura;
		this.user = user;
	}

	/**
	 * Getter do parâmetro id
	 * 
	 * @return Id do cartão de crédito
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * Setter do parâmetro id
	 * 
	 * @param id - Id do cartão de crédito
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * Getter do parâmetro nome
	 * 
	 * @return Nome do cartão de crédito
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Setter do parâmetro nome
	 * 
	 * @param nome - Nome do cartão de crédito
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * Getter do parâmetro bandeiraCartaoDeCredito
	 * 
	 * @return Bandeira do cartão de crédito
	 */
	public BandeiraCartaoDeCredito getBandeiraCartaoDeCredito() {
		return bandeiraCartaoDeCredito;
	}

	/**
	 * Setter do parâmetro bandeiraCartaoDeCredito
	 * 
	 * @param BandeiraCartaoDeCredito - Bandeira do cartão de crédito
	 */
	public void setBandeiraCartaoDeCredito(BandeiraCartaoDeCredito BandeiraCartaoDeCredito) {
		this.bandeiraCartaoDeCredito = BandeiraCartaoDeCredito;
	}

	/**
	 * Getter do parâmetro limite
	 * 
	 * @return Limite do cartão de crédito
	 */
	public float getLimite() {
		return limite;
	}

	/**
	 * Setter do parâmetro limite
	 * 
	 * @param limite - Limite do cartão de crédito
	 */
	public void setLimite(float limite) {
		this.limite = limite;
	}

	/**
	 * Getter do parâmetro diaFechamentoFatura
	 * 
	 * @return Dia de fechamento da fatura
	 */
	public Integer getDiaFechamentoFatura() {
		return diaFechamentoFatura;
	}

	/**
	 * Setter do parâmetro diaFechamentoFatura
	 * 
	 * @param diaFechamentoFatura - Dia de fechamento da fatura
	 */
	public void setDiaFechamentoFatura(Integer diaFechamentoFatura) {
		this.diaFechamentoFatura = diaFechamentoFatura;
	}

	/**
	 * Getter do parâmetro diaVencimentoFatura
	 * 
	 * @return Dia de vencimento da fatura
	 */
	public Integer getDiaVencimentoFatura() {
		return diaVencimentoFatura;
	}

	/**
	 * Setter do parâmetro diaVencimentoFatura
	 * 
	 * @param diaVencimentoFatura - Dia de vencimento da fatura
	 */
	public void setDiaVencimentoFatura(Integer diaVencimentoFatura) {
		this.diaVencimentoFatura = diaVencimentoFatura;
	}

	/**
	 * Getter do parâmetro user
	 * 
	 * @return Usuário que cadastrou o cartão de crédito
	 */
	public User getUser() {
		return user;
	}

	/**
	 * Setter do parâmetro user
	 * 
	 * @param user - Usuário que cadastrou o cartão de crédito
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * Getter das despesas vinculadas ao cartão de crédito
	 * 
	 * @return Lista das despesas vinculadas ao cartão de crédito
	 */
	public List<DespesaCredito> getDespesasCredito() {
		return despesasCredito;
	}

	/**
	 * Setter das despesas vinculadas ao cartão de crédito
	 * 
	 * @param despesasCredito - Lista de despesas vinculadas ao cartão de crédito
	 */
	public void setDespesasCredito(List<DespesaCredito> despesasCredito) {
		this.despesasCredito = despesasCredito;
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
		CartaoDeCredito other = (CartaoDeCredito) obj;
		return Objects.equals(id, other.id);
	}

}
