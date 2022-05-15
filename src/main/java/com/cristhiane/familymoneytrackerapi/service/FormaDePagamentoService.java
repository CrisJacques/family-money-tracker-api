package com.cristhiane.familymoneytrackerapi.service;

import java.util.Hashtable;

import org.springframework.stereotype.Service;

import com.cristhiane.familymoneytrackerapi.enums.FormaDePagamento;

/**
 * Classe que contém os services relacionados à entity Forma de pagamento
 *
 */
@Service
public class FormaDePagamentoService {	
	/**
	 * Busca por todas as formas de pagamento cadastradas
	 * @return Lista contendo informações de todas as formas de pagamento cadastradas
	 */
	public Hashtable<Integer, Object> findAll() {
		int i = 0;
		Hashtable<Integer, Object> formasDePagamento = new Hashtable<Integer, Object>();
		for (FormaDePagamento fp : FormaDePagamento.values()) {
			Hashtable<Object, Object> formaDePagamento = new Hashtable<Object, Object>();
			
			formaDePagamento.put("cod", fp.getCod());
			formaDePagamento.put("descricao", fp.getDescricao());
			
			formasDePagamento.put(i, formaDePagamento);
			i++;
		}
		
		return formasDePagamento;
	}
}
