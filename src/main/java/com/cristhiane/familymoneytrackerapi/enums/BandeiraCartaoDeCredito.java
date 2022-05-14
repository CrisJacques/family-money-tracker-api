package com.cristhiane.familymoneytrackerapi.enums;

/**
 * Enum que lista as opções de Bandeiras de cartão de crédito disponíveis na
 * aplicação
 *
 */
public enum BandeiraCartaoDeCredito {
	MASTERCARD(0, "Mastercard"), VISA(1, "Visa"), HIPERCARD(2, "Hipercard"), ELO(3, "Elo"),
	AMERICAN_EXPRESS(4, "American Express"), DINERS_CLUB(5, "Diners Club"), OUTRO(6, "Outro");

	private int cod;
	private String descricao;

	/**
	 * Construtor
	 * 
	 * @param cod       - Código numérico da bandeira de cartão de crédito
	 * @param descricao - Nome da bandeira de cartão de crédito
	 */
	private BandeiraCartaoDeCredito(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

	/**
	 * Busca a bandeira de cartão de crédito correspondente ao código passado por
	 * parâmetro, caso exista
	 * 
	 * @param cod - Código numérico da bandeira de cartão de crédito
	 * @return Nome da bandeira de cartão de crédito correspondente ao código
	 *         numérico, caso exista
	 * @throws IllegalArgumentException Caso o código passado não corresponda a
	 *                                  nenhuma bandeira de cartão de crédito
	 */
	public static BandeiraCartaoDeCredito toEnum(Integer cod) {
		if (cod == null) {
			return null;
		}

		for (BandeiraCartaoDeCredito bcc : BandeiraCartaoDeCredito.values()) {
			if (cod.equals(bcc.getCod())) {
				return bcc;
			}
		}

		throw new IllegalArgumentException();
	}

	/**
	 * Getter do parâmetro cod
	 * 
	 * @return Código numérico da bandeira de cartão de crédito
	 */
	public int getCod() {
		return cod;
	}

	/**
	 * Setter do parâmetro cod
	 * 
	 * @param cod - Código numérico da bandeira de cartão de crédito
	 */
	public void setCod(int cod) {
		this.cod = cod;
	}

	/**
	 * Getter do parâmetro descricao
	 * 
	 * @return Nome da bandeira de cartão de crédito
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * Setter do parâmetro descricao
	 * 
	 * @param descricao - Nome da bandeira de cartão de crédito
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
