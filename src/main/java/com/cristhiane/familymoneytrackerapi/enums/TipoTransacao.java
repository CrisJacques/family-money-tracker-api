package com.cristhiane.familymoneytrackerapi.enums;

public enum TipoTransacao {
	DESPESA(1, "Despesa"),
    RECEITA(2, "Receita");
    
    private int cod;
	private String descricao;
	
	private TipoTransacao(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}
	
	public static TipoTransacao toEnum(Integer cod) {
		if (cod == null) {
			return null;
		}
		
		for (TipoTransacao tt : TipoTransacao.values()) {
			if (cod.equals(tt.getCod())){
				return tt;
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
