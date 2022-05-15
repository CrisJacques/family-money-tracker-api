package com.cristhiane.familymoneytrackerapi.service.exceptions;

/**
 * Classe para construir exceção que deve ser lançada quando algum registro não
 * puder removido por afetar a integridade dos dados
 *
 */
public class DataIntegrityException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public DataIntegrityException(String msg) {
		super(msg); // passando a mensagem recebida por parâmetro no construtor para o construtor da
					// classe mãe
	}

	public DataIntegrityException(String msg, Throwable cause) {
		super(msg, cause);
	}

}
