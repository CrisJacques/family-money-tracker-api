package com.cristhiane.familymoneytrackerapi.enums;

/**
 * Enum que lista as opções de Bancos disponíveis na aplicação
 *
 */
public enum Banco {
	BANCO_DO_BRASIL(0, "Banco do Brasil"), CAIXA(1, "Caixa"), ITAU(2, "Itaú"), SANTANDER(3, "Santander"),
	BRADESCO(4, "Bradesco"), OUTRO(5, "Outro");

	private int cod;
	private String descricao;

	/**
	 * Construtor
	 * 
	 * @param cod       - Código numérico do banco
	 * @param descricao - Nome do banco
	 */
	private Banco(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

	/**
	 * Busca o banco correspondente ao código passado por parâmetro, caso exista
	 * 
	 * @param cod - Código numérico do banco
	 * @return Nome do banco correspondente ao código numérico, caso exista
	 * @throws IllegalArgumentException Caso o código passado não corresponda a
	 *                                  nenhum banco
	 */
	public static Banco toEnum(Integer cod) {
		if (cod == null) {
			return null;
		}

		for (Banco b : Banco.values()) {
			if (cod.equals(b.getCod())) {
				return b;
			}
		}

		throw new IllegalArgumentException();
	}

	/**
	 * Getter do parâmetro cod
	 * 
	 * @return Código numérico do banco
	 */
	public int getCod() {
		return cod;
	}

	/**
	 * Setter do parâmetro cod
	 * 
	 * @param cod - Código numérico do banco
	 */
	public void setCod(int cod) {
		this.cod = cod;
	}

	/**
	 * Getter do parâmetro descricao
	 * 
	 * @return Nome do banco
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * Setter do parâmetro descricao
	 * 
	 * @param descricao - Nome do banco
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
