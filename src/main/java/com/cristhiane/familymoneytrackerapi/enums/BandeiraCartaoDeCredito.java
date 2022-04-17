package com.cristhiane.familymoneytrackerapi.enums;

public enum BandeiraCartaoDeCredito {
	MASTERCARD(0, "Mastercard"),
	VISA(1, "Visa"),
	HIPERCARD(2, "Hipercard"),
	ELO(3, "Elo"),
	AMERICAN_EXPRESS(4, "American Express"),
	DINERS_CLUB(5, "Diners Club"),
	OUTRO(6, "Outro");
	
	private int cod;
	private String descricao;
	
	private BandeiraCartaoDeCredito(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}
	
	public static BandeiraCartaoDeCredito toEnum(Integer cod) {
		if (cod == null) {
			return null;
		}
		
		for (BandeiraCartaoDeCredito bcc : BandeiraCartaoDeCredito.values()) {
			if (cod.equals(bcc.getCod())){
				return bcc;
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

