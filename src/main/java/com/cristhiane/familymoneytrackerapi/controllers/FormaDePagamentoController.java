package com.cristhiane.familymoneytrackerapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cristhiane.familymoneytrackerapi.enums.FormaDePagamento;
import com.cristhiane.familymoneytrackerapi.service.FormaDePagamentoService;

@RestController
@RequestMapping(value = "/api/formas-de-pagamento")
public class FormaDePagamentoController {

	@Autowired
	FormaDePagamentoService formaDePagamentoService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<FormaDePagamento[]> findAll() {
		FormaDePagamento[] list = formaDePagamentoService.findAll(); 
		return ResponseEntity.ok().body(list);
	}
}
