package com.cristhiane.familymoneytrackerapi.service;

import java.util.Hashtable;

import org.springframework.stereotype.Service;

import com.cristhiane.familymoneytrackerapi.enums.FormaDePagamento;

@Service
public class FormaDePagamentoService {	
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
