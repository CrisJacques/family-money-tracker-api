package com.cristhiane.familymoneytrackerapi.enums;

/**
 * Enum que lista as opções de Forma de pagamento disponíveis na aplicação
 *
 */
public enum FormaDePagamento {
	DINHEIRO(0, "Dinheiro"), DEBITO(1, "Débito"), CARTAO_DE_CREDITO(2, "Cartão de Crédito"),
	FINANCIAMENTO(3, "Financiamento"), EMPRESTIMO(4, "Empréstimo");

	private int cod;
	private String descricao;

	/**
	 * Construtor
	 * 
	 * @param cod       - Código numérico da forma de pagamento
	 * @param descricao - Nome da forma de pagamento
	 */
	private FormaDePagamento(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

	/**
	 * Busca a forma de pagamento correspondente ao código passado por parâmetro,
	 * caso exista
	 * 
	 * @param cod - Código numérico da forma de pagamento
	 * @return Nome da forma de pagamento correspondente ao código numérico, caso
	 *         exista
	 * @throws IllegalArgumentException Caso o código passado não corresponda a
	 *                                  nenhuma forma de pagamento
	 */
	public static FormaDePagamento toEnum(Integer cod) {
		if (cod == null) {
			return null;
		}

		for (FormaDePagamento fp : FormaDePagamento.values()) {
			if (cod.equals(fp.getCod())) {
				return fp;
			}
		}

		throw new IllegalArgumentException();
	}

	/**
	 * Getter do parâmetro cod
	 * 
	 * @return Código numérico da forma de pagamento
	 */
	public int getCod() {
		return cod;
	}

	/**
	 * Setter do parâmetro cod
	 * 
	 * @param cod - Código numérico da forma de pagamento
	 */
	public void setCod(int cod) {
		this.cod = cod;
	}

	/**
	 * Getter do parâmetro descricao
	 * 
	 * @return Nome da forma de pagamento
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * Setter do parâmetro descricao
	 * 
	 * @param descricao - Nome da forma de pagamento
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}