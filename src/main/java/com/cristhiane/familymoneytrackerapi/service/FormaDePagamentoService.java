package com.cristhiane.familymoneytrackerapi.service;

import org.springframework.stereotype.Service;

import com.cristhiane.familymoneytrackerapi.enums.FormaDePagamento;

@Service
public class FormaDePagamentoService {
	public FormaDePagamento[] findAll() {
		return FormaDePagamento.values();
	}
}
