package com.cristhiane.familymoneytrackerapi.enums;

public enum TipoConta {
	DINHEIRO(0, "Dinheiro"),
	CONTA_CORRENTE(1, "Conta Corrente"),
	INVESTIMENTO(2, "Investimento"),
	CONTA_POUPANCA(3, "Conta Poupança"),
	MESADA(4, "Mesada"),
	OUTRO(5, "Outro");
	
	private int cod;
	private String descricao;
	
	private TipoConta(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}
	
	public static TipoConta toEnum(Integer cod) {
		if (cod == null) {
			return null;
		}
		
		for (TipoConta tc : TipoConta.values()) {
			if (cod.equals(tc.getCod())){
				return tc;
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
