package com.cristhiane.familymoneytrackerapi.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.apache.commons.math3.util.Precision;

import com.cristhiane.familymoneytrackerapi.domain.Despesa;

/**
 * Classe que constrói um DTO (Data Transfer Object) para as despesas
 *
 */
public class DespesaDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private float valor;
	private String descricao;
	private LocalDate data;
	private String stringData;
	private String nomeCategoriaDespesa;
	private String tipo;
	private String formaDePagamentoDesc;
	private String formaDePagamentoName;
	

	/**
	 * Construtor vazio
	 */
	public DespesaDTO() {

	}

	/**
	 * Construtor com parâmetros - constrói o DTO apenas com as informações
	 * essenciais para exibição de uma despesa
	 * 
	 * @param obj - Objeto com todas as propriedades da despesa
	 */
	public DespesaDTO(Despesa obj) {
		super();

		this.id = obj.getId();
		this.valor = Precision.round(obj.getValor(), 2);
		this.descricao = obj.getDescricao();
		this.data = obj.getData();
		this.stringData = this.data.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
		this.nomeCategoriaDespesa = obj.getCategoriaDespesa().getNome();
		this.tipo = "Despesa";
		this.formaDePagamentoDesc = obj.getFormaDePagamento().getDescricao();
		this.formaDePagamentoName = obj.getFormaDePagamento().name();
	}

	/**
	 * Getter do parâmetro id
	 * 
	 * @return Id da despesa
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * Setter do parâmetro id
	 * 
	 * @param id - Id da despesa
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * Getter do parâmetro valor
	 * 
	 * @return Valor da despesa
	 */
	public float getValor() {
		return valor;
	}

	/**
	 * Setter do parâmetro valor
	 * 
	 * @param valor - Valor da despesa
	 */
	public void setValor(float valor) {
		this.valor = valor;
	}

	/**
	 * Getter do parâmetro descricao
	 * 
	 * @return Descrição da despesa
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * Setter do parâmetro descricao
	 * 
	 * @param descricao - Descrição da despesa
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	/**
	 * Getter do parâmetro data
	 * 
	 * @return Data da despesa
	 */
	public LocalDate getData() {
		return data;
	}

	/**
	 * Setter do parâmetro data
	 * 
	 * @param data - Data da despesa
	 */
	public void setData(LocalDate data) {
		this.data = data;
	}

	/**
	 * Getter do parâmetro nomeCategoriaDespesa
	 * 
	 * @return Nome da categoria da despesa
	 */
	public String getNomeCategoriaDespesa() {
		return nomeCategoriaDespesa;
	}

	/**
	 * Setter do parâmetro nomeCategoriaDespesa
	 * 
	 * @param nomeCategoriaDespesa - Nome da categoria da despesa
	 */
	public void setNomeCategoriaDespesa(String nomeCategoriaDespesa) {
		this.nomeCategoriaDespesa = nomeCategoriaDespesa;
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
	 * Getter do parâmetro stringData
	 * 
	 * @return Data da despesa em formato adequado para exibição
	 */
	public String getStringData() {
		return stringData;
	}

	/**
	 * Setter do parâmetro stringData
	 * 
	 * @param stringData - Data da despesa em formato adequado para exibição
	 */
	public void setStringData(String stringData) {
		this.stringData = stringData;
	}
	
	/**
	 * Getter do parâmetro formaDePagamentoDesc
	 * 
	 * @return Descrição da forma de pagamento da despesa
	 */
	public String getFormaDePagamentoDesc() {
		return formaDePagamentoDesc;
	}

	/**
	 * Setter do parâmetro formaDePagamentoDesc
	 * 
	 * @param formaDePagamentoDesc - Descrição da forma de pagamento da despesa
	 */
	public void setFormaDePagamentoDesc(String formaDePagamentoDesc) {
		this.formaDePagamentoDesc = formaDePagamentoDesc;
	}
	
	/**
	 * Getter do parâmetro formaDePagamentoName
	 * 
	 * @return Nome da forma de pagamento da despesa
	 */
	public String getFormaDePagamentoName() {
		return formaDePagamentoName;
	}

	
	/**
	 * Setter do parâmetro formaDePagamentoName
	 * 
	 * @param formaDePagamentoName - Nome da forma de pagamento da despesa
	 */
	public void setFormaDePagamentoName(String formaDePagamentoName) {
		this.formaDePagamentoName = formaDePagamentoName;
	}


}
