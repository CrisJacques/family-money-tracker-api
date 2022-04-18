package com.cristhiane.familymoneytrackerapi.enums;

public enum FormaDePagamento {
	DINHEIRO(0, "Dinheiro"),
	DEBITO(1, "Débito"),
	CARTAO_DE_CREDITO(2, "Cartão de Crédito"),
	FINANCIAMENTO(3, "Financiamento"),
	EMPRESTIMO(4, "Empréstimo");
	
	private int cod;
	private String descricao;
	
	private FormaDePagamento(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}
	
	public static FormaDePagamento toEnum(Integer cod) {
		if (cod == null) {
			return null;
		}
		
		for (FormaDePagamento fp : FormaDePagamento.values()) {
			if (cod.equals(fp.getCod())){
				return fp;
			}
		}
		
		throw new IllegalArgumentException();
	}

	public int getCod() {
		return cod;
	}

	public void setCod(int cod) {
		this.cod = cod;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
}