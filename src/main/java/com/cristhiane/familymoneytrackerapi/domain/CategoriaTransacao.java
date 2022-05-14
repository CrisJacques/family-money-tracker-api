package com.cristhiane.familymoneytrackerapi.domain;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

/**
 * Classe que representa a entity Categoria de transação
 *
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class CategoriaTransacao implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	private String descricao;

	/**
	 * Construtor vazio
	 */
	public CategoriaTransacao() {

	}

	/**
	 * Construtor com parâmetros
	 * 
	 * @param id        - Id da categoria de transação
	 * @param nome      - Nome da categoria de transação
	 * @param descricao - Descrição da categoria de transação
	 */
	public CategoriaTransacao(Integer id, String nome, String descricao) {
		super();
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
	}

	/**
	 * Getter do parâmetro id
	 * 
	 * @return Id da categoria de transação
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * Setter do parâmetro id
	 * 
	 * @param id - Id da categoria de transação
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * Getter do parâmetro nome
	 * 
	 * @return Nome da categoria de transação
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Setter do parâmetro nome
	 * 
	 * @param nome - Nome da categoria de transação
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * Getter do parâmetro descricao
	 * 
	 * @return Descrição da categoria de transação
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * Setter do parâmetro descricao
	 * 
	 * @param descricao - Descrição da categoria de transação
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
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
