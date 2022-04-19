package com.cristhiane.familymoneytrackerapi.enums;

public enum Banco {
	BANCO_DO_BRASIL(0, "Banco do Brasil"),
	CAIXA(1, "Caixa"),
	ITAU(2, "Itaú"),
	SANTANDER(3, "Santander"),
	BRADESCO(4, "Bradesco"),
	OUTRO(5, "Outro");
	
	private int cod;
	private String descricao;
	
	private Banco(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}
	
	public static Banco toEnum(Integer cod) {
		if (cod == null) {
			return null;
		}
		
		for (Banco b : Banco.values()) {
			if (cod.equals(b.getCod())){
				return b;
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
