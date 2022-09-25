package com.cristhiane.familymoneytrackerapi.dto;

import java.io.Serializable;
import java.time.format.DateTimeFormatter;

import org.apache.commons.math3.util.Precision;

import com.cristhiane.familymoneytrackerapi.domain.Conta;
import com.cristhiane.familymoneytrackerapi.domain.Receita;

/**
 * Classe que constrói um DTO (Data Transfer Object) para as receitas
 *
 */
public class ReceitaDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private float valor;
	private String descricao;
	private String data;
	private String nomeCategoriaReceita;
	private Integer idCategoriaReceita;
	private String tipo;
	private Conta conta;

	/**
	 * Construtor vazio
	 */
	public ReceitaDTO() {

	}

	/**
	 * Construtor com parâmetros - constrói o DTO apenas com as informações
	 * essenciais para exibição de uma receita
	 * 
	 * @param obj - Objeto com todas as propriedades da receita
	 */
	public ReceitaDTO(Receita obj) {
		super();

		this.id = obj.getId();
		this.valor = Precision.round(obj.getValor(), 2);
		this.descricao = obj.getDescricao();
		this.data = obj.getData().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
		this.nomeCategoriaReceita = obj.getCategoriaReceita().getNome();
		this.idCategoriaReceita = obj.getCategoriaReceita().getId();
		this.tipo = "Receita";
		this.conta = obj.getConta();
	}

	/**
	 * Getter do parâmetro id
	 * 
	 * @return Id da receita
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * Setter do parâmetro id
	 * 
	 * @param id - Id da receita
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * Getter do parâmetro valor
	 * 
	 * @return Valor da receita
	 */
	public float getValor() {
		return valor;
	}

	/**
	 * Setter do parâmetro valor
	 * 
	 * @param valor - Valor da receita
	 */
	public void setValor(float valor) {
		this.valor = valor;
	}

	/**
	 * Getter do parâmetro descricao
	 * 
	 * @return Descrição da receita
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * Setter do parâmetro descricao
	 * 
	 * @param descricao - Descrição da receita
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	/**
	 * Getter do parâmetro data
	 * 
	 * @return Data da receita
	 */
	public String getData() {
		return data;
	}

	/**
	 * Setter do parâmetro data
	 * 
	 * @param data - Data da receita
	 */
	public void setData(String data) {
		this.data = data;
	}

	/**
	 * Getter do parâmetro nomeCategoriaReceita
	 * 
	 * @return Nome da categoria da receita
	 */
	public String getNomeCategoriaReceita() {
		return nomeCategoriaReceita;
	}

	/**
	 * Setter do parâmetro nomeCategoriaReceita
	 * 
	 * @param nomeCategoriaReceita - Nome da categoria da receita
	 */
	public void setNomeCategoriaReceita(String nomeCategoriaReceita) {
		this.nomeCategoriaReceita = nomeCategoriaReceita;
	}

	/**
	 * Getter do parâmetro tipo
	 * 
	 * @return Tipo de transação
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * Setter do parâmetro tipo
	 * 
	 * @param tipo - Tipo de transação
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	/**
	 * Getter do parâmetro conta
	 * 
	 * @return Conta vinculada à receita
	 */
	public Conta getConta() {
		return conta;
	}

	/**
	 * Setter do parâmetro conta
	 * 
	 * @param conta - Conta vinculada à receita
	 */
	public void setConta(Conta conta) {
		this.conta = conta;
	}

	/**
	 * Getter do parâmetro idCategoriaReceita
	 * 
	 * @return Id numérico da categoria da receita
	 */
	public Integer getIdCategoriaReceita() {
		return idCategoriaReceita;
	}

	/**
	 * Setter do parâmetro idCategoriaReceita
	 * 
	 * @param idCategoriaReceita - Id numérico da categoria da receita
	 */
	public void setIdCategoriaReceita(Integer idCategoriaReceita) {
		this.idCategoriaReceita = idCategoriaReceita;
	}

}
