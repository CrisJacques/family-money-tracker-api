package com.cristhiane.familymoneytrackerapi.domain;

import javax.persistence.Entity;

import com.cristhiane.familymoneytrackerapi.enums.TipoTransacao;

@Entity
public class CategoriaReceita extends CategoriaTransacao {

	private static final long serialVersionUID = 1L;

	public CategoriaReceita() {
		
	}
	
	public CategoriaReceita(Integer id, String nome, String descricao, TipoTransacao tipoTransacao) {
		super(id, nome, descricao, tipoTransacao);
	}

}
