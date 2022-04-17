package com.cristhiane.familymoneytrackerapi.domain;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.cristhiane.familymoneytrackerapi.enums.BandeiraCartaoDeCredito;

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
	
	public CartaoDeCredito() {
		
	}

	public CartaoDeCredito(Integer id, String nome, BandeiraCartaoDeCredito bandeiraCartaoDeCredito, float limite,
			Integer diaFechamentoFatura, Integer diaVencimentoFatura) {
		super();
		this.id = id;
		this.nome = nome;
		this.bandeiraCartaoDeCredito = bandeiraCartaoDeCredito;
		this.limite = limite;
		this.diaFechamentoFatura = diaFechamentoFatura;
		this.diaVencimentoFatura = diaVencimentoFatura;
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

	public BandeiraCartaoDeCredito getBandeiraCartaoDeCredito() {
		return bandeiraCartaoDeCredito;
	}

	public void setBandeiraCartaoDeCredito(BandeiraCartaoDeCredito BandeiraCartaoDeCredito) {
		this.bandeiraCartaoDeCredito = BandeiraCartaoDeCredito;
	}

	public float getLimite() {
		return limite;
	}

	public void setLimite(float limite) {
		this.limite = limite;
	}

	public Integer getDiaFechamentoFatura() {
		return diaFechamentoFatura;
	}

	public void setDiaFechamentoFatura(Integer diaFechamentoFatura) {
		this.diaFechamentoFatura = diaFechamentoFatura;
	}

	public Integer getDiaVencimentoFatura() {
		return diaVencimentoFatura;
	}

	public void setDiaVencimentoFatura(Integer diaVencimentoFatura) {
		this.diaVencimentoFatura = diaVencimentoFatura;
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
