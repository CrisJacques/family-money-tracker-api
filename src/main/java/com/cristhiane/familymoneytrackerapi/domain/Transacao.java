package com.cristhiane.familymoneytrackerapi.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Transacao implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private float valor;
	private String descricao;
	@Temporal(TemporalType.DATE)
	private Date data;
	private boolean recorrente;
	private Integer diaLancamentoRecorrente;
	
	public Transacao() {
		
	}

	public Transacao(Integer id, float valor, String descricao, Date data, boolean recorrente,
			Integer diaLancamentoRecorrente) {
		super();
		this.id = id;
		this.valor = valor;
		this.descricao = descricao;
		this.data = data;
		this.recorrente = recorrente;
		this.diaLancamentoRecorrente = diaLancamentoRecorrente;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public float getValor() {
		return valor;
	}

	public void setValor(float valor) {
		this.valor = valor;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public boolean isRecorrente() {
		return recorrente;
	}

	public void setRecorrente(boolean recorrente) {
		this.recorrente = recorrente;
	}

	public Integer getDiaLancamentoRecorrente() {
		return diaLancamentoRecorrente;
	}

	public void setDiaLancamentoRecorrente(Integer diaLancamentoRecorrente) {
		this.diaLancamentoRecorrente = diaLancamentoRecorrente;
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
		Transacao other = (Transacao) obj;
		return Objects.equals(id, other.id);
	}

}
