package com.cristhiane.familymoneytrackerapi.dto;

import java.io.Serializable;
import java.time.format.DateTimeFormatter;

import org.apache.commons.math3.util.Precision;

import com.cristhiane.familymoneytrackerapi.domain.Receita;

public class ReceitaDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private float valor;
	private String descricao;
	private String data;
	private String nomeCategoriaReceita;
	private String tipo;
	
	public ReceitaDTO() {
		
	}

	public ReceitaDTO(Receita obj) {
		super();
		
		this.id = obj.getId();
		this.valor = Precision.round(obj.getValor(), 2);
		this.descricao = obj.getDescricao();
		this.data = obj.getData().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
		this.nomeCategoriaReceita = obj.getCategoriaReceita().getNome();
		this.tipo = "Receita";
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

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getNomeCategoriaReceita() {
		return nomeCategoriaReceita;
	}

	public void setNomeCategoriaReceita(String nomeCategoriaReceita) {
		this.nomeCategoriaReceita = nomeCategoriaReceita;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

}
