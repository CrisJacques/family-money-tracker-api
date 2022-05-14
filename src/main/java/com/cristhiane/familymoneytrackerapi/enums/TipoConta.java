package com.cristhiane.familymoneytrackerapi.enums;

/**
 * Enum que lista as opções de tipo de conta disponíveis na aplicação
 *
 */
public enum TipoConta {
	DINHEIRO(0, "Dinheiro"), CONTA_CORRENTE(1, "Conta Corrente"), INVESTIMENTO(2, "Investimento"),
	CONTA_POUPANCA(3, "Conta Poupança"), MESADA(4, "Mesada"), OUTRO(5, "Outro");

	private int cod;
	private String descricao;

	/**
	 * Construtor
	 * 
	 * @param cod       - Código numérico do tipo de conta
	 * @param descricao - Nome do tipo de conta
	 */
	private TipoConta(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

	/**
	 * Busca o tipo de conta correspondente ao código passado por parâmetro, caso
	 * exista
	 * 
	 * @param cod - Código numérico do tipo de conta
	 * @return Nome do tipo de conta correspondente ao código numérico, caso exista
	 * @throws IllegalArgumentException Caso o código passado não corresponda a
	 *                                  nenhum tipo de conta
	 */
	public static TipoConta toEnum(Integer cod) {
		if (cod == null) {
			return null;
		}

		for (TipoConta tc : TipoConta.values()) {
			if (cod.equals(tc.getCod())) {
				return tc;
			}
		}

		throw new IllegalArgumentException();
	}

	/**
	 * Getter do parâmetro cod
	 * 
	 * @return Código numérico do tipo de conta
	 */
	public int getCod() {
		return cod;
	}

	/**
	 * Setter do parâmetro cod
	 * 
	 * @param cod - Código numérico do tipo de conta
	 */
	public void setCod(int cod) {
		this.cod = cod;
	}

	/**
	 * Getter do parâmetro descricao
	 * 
	 * @return Nome do tipo de conta
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * Setter do parâmetro descricao
	 * 
	 * @param descricao - Nome do tipo de conta
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
