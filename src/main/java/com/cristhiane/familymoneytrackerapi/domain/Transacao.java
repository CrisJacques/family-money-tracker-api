package com.cristhiane.familymoneytrackerapi.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import org.apache.commons.math3.util.Precision;

/**
 * Classe que representa a entity Transação
 *
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Transacao implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private float valor;
	private String descricao;
	private LocalDate data;
	private boolean recorrente;
	private Integer diaLancamentoRecorrente;

	/**
	 * Construtor vazio
	 */
	public Transacao() {

	}

	/**
	 * Construtor com parâmetros
	 * 
	 * @param id                      - Id da transação
	 * @param valor                   - Valor da transação
	 * @param descricao               - Descrição da transação
	 * @param data                    - Data da transação
	 * @param recorrente              - Indica se transação se repete todo mês ou
	 *                                não
	 * @param diaLancamentoRecorrente - Dia que transação deve ser lançada todo mês
	 *                                caso ela seja recorrente
	 */
	public Transacao(Integer id, float valor, String descricao, LocalDate data, boolean recorrente,
			Integer diaLancamentoRecorrente) {
		super();
		this.id = id;
		this.valor = Precision.round(valor, 2);
		this.descricao = descricao;
		this.data = data;
		this.recorrente = recorrente;
		this.diaLancamentoRecorrente = diaLancamentoRecorrente;
	}

	/**
	 * Getter do parâmetro id
	 * 
	 * @return Id da transação
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * Setter do parâmetro id
	 * 
	 * @param id - Id da transação
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * Getter do parâmetro valor
	 * 
	 * @return Valor da transação
	 */
	public float getValor() {
		return valor;
	}

	/**
	 * Setter do parâmetro valor
	 * 
	 * @param valor - Valor da transação
	 */
	public void setValor(float valor) {
		this.valor = Precision.round(valor, 2);
	}

	/**
	 * Getter do parâmetro descricao
	 * 
	 * @return Descrição da transação
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * Setter do parâmetro descricao
	 * 
	 * @param descricao - Descrição da transação
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	/**
	 * Getter do parâmetro data
	 * 
	 * @return Data da transação
	 */
	public LocalDate getData() {
		return data;
	}

	/**
	 * Setter do parâmetro data
	 * 
	 * @param data - Data da transação
	 */
	public void setData(LocalDate data) {
		this.data = data;
	}

	/**
	 * Getter do parâmetro recorrente
	 * 
	 * @return Se transação é recorrente
	 */
	public boolean isRecorrente() {
		return recorrente;
	}

	/**
	 * Setter do parâmetro recorrente
	 * 
	 * @param recorrente - Se transação é recorrente
	 */
	public void setRecorrente(boolean recorrente) {
		this.recorrente = recorrente;
	}

	/**
	 * Getter do parâmetro diaLancamentoRecorrente
	 * 
	 * @return Dia que transação deve ser lançada todo mês caso ela seja recorrente
	 */
	public Integer getDiaLancamentoRecorrente() {
		return diaLancamentoRecorrente;
	}

	/**
	 * Setter do parâmetro diaLancamentoRecorrente
	 * 
	 * @param diaLancamentoRecorrente - Dia que transação deve ser lançada todo mês
	 *                                caso ela seja recorrente
	 */
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
