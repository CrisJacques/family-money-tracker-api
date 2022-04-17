package com.cristhiane.familymoneytrackerapi.domain;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import com.cristhiane.familymoneytrackerapi.enums.TipoTransacao;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class CategoriaTransacao implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	private String descricao;
	private TipoTransacao tipoTransacao;
	
	public CategoriaTransacao() {
		
	}

	public CategoriaTransacao(Integer id, String nome, String descricao, TipoTransacao tipoTransacao) {
		super();
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.tipoTransacao = tipoTransacao;
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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public TipoTransacao getTipoTransacao() {
		return tipoTransacao;
	}

	public void setTipoTransacao(TipoTransacao tipoTransacao) {
		this.tipoTransacao = tipoTransacao;
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
		CategoriaTransacao other = (CategoriaTransacao) obj;
		return Objects.equals(id, other.id);
	}
}
