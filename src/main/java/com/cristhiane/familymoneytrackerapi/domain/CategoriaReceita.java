package com.cristhiane.familymoneytrackerapi.domain;

import javax.persistence.Entity;


@Entity
public class CategoriaReceita extends CategoriaTransacao {

	private static final long serialVersionUID = 1L;

	public CategoriaReceita() {
		
	}
	
	public CategoriaReceita(Integer id, String nome, String descricao) {
		super(id, nome, descricao);
	}

}
