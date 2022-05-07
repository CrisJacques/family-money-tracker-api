package com.cristhiane.familymoneytrackerapi.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.cristhiane.familymoneytrackerapi.domain.Despesa;

public class DespesaDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private float valor;
	private String descricao;
	private LocalDate data;
	private String stringData;
	private String nomeCategoriaDespesa;
	private String tipo;
	
	public DespesaDTO() {
		
	}

	public DespesaDTO(Despesa obj) {
		super();
		
		this.id = obj.getId();
		this.valor = obj.getValor();
		this.descricao = obj.getDescricao();
		this.data = obj.getData();
		this.stringData = this.data.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
		this.nomeCategoriaDespesa = obj.getCategoriaDespesa().getNome();
		this.tipo = "Despesa";
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

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public String getNomeCategoriaDespesa() {
		return nomeCategoriaDespesa;
	}

	public void setNomeCategoriaDespesa(String nomeCategoriaDespesa) {
		this.nomeCategoriaDespesa = nomeCategoriaDespesa;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getStringData() {
		return stringData;
	}

	public void setStringData(String stringData) {
		this.stringData = stringData;
	}

}
