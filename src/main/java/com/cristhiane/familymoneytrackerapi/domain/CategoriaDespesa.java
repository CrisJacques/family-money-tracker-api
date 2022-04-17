package com.cristhiane.familymoneytrackerapi.domain;

import javax.persistence.Entity;

@Entity
public class CategoriaDespesa extends CategoriaTransacao {
	private static final long serialVersionUID = 1L;
	private float limite;
	
	public CategoriaDespesa() {
		
	}

	public CategoriaDespesa(Integer id, String nome, String descricao, float limite) {
		super(id, nome, descricao);
		this.limite = limite;
	}

	public float getLimite() {
		return limite;
	}

	public void setLimite(float limite) {
		this.limite = limite;
	}	
}
