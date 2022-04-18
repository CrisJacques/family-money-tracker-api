package com.cristhiane.familymoneytrackerapi.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class CategoriaDespesa extends CategoriaTransacao {
	private static final long serialVersionUID = 1L;
	private float limite;
	
	@ManyToOne
	@JoinColumn(name = "id_usuario")
	private User user;
	
	@JsonIgnore
	@OneToMany(mappedBy = "categoriaDespesa")
	private List<Despesa> despesas = new ArrayList<>();
	
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

	public List<Despesa> getDespesas() {
		return despesas;
	}

	public void setDespesas(List<Despesa> despesas) {
		this.despesas = despesas;
	}	
	
}
