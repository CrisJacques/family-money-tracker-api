package com.cristhiane.familymoneytrackerapi.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


@Entity
public class CategoriaReceita extends CategoriaTransacao {

	private static final long serialVersionUID = 1L;
	
	@OneToMany(mappedBy = "categoriaReceita")
	private List<Receita> receitas = new ArrayList<>();
	
	@ManyToOne
	@JoinColumn(name = "id_usuario")
	private User user;

	public CategoriaReceita() {
		
	}
	
	public CategoriaReceita(Integer id, String nome, String descricao, User user) {
		super(id, nome, descricao);
		this.user = user;
	}

	public List<Receita> getReceitas() {
		return receitas;
	}

	public void setReceitas(List<Receita> receitas) {
		this.receitas = receitas;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
