package com.cristhiane.familymoneytrackerapi.domain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class CategoriaDespesa extends CategoriaTransacao {
	private static final long serialVersionUID = 1L;
	private float limite;
	
	@ManyToOne
	@JoinColumn(name = "id_usuario")
	private User user;
	
	public CategoriaDespesa() {
		
	}

	public CategoriaDespesa(Integer id, String nome, String descricao, float limite, User user) {
		super(id, nome, descricao);
		this.limite = limite;
		this.user = user;
	}

	public float getLimite() {
		return limite;
	}

	public void setLimite(float limite) {
		this.limite = limite;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}	
	
	
}
