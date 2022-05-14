package com.cristhiane.familymoneytrackerapi.controllers;

import java.util.Hashtable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cristhiane.familymoneytrackerapi.service.FormaDePagamentoService;

/**
 * Controller responsável pelas requisições relacionadas a formas de pagamento
 *
 */
@RestController
@RequestMapping(value = "/api/formas-de-pagamento")
public class FormaDePagamentoController {

	@Autowired
	FormaDePagamentoService formaDePagamentoService;

	/**
	 * Busca por todas as formas de pagamento cadastradas
	 * 
	 * @return Lista de todas as formas de pagamento cadastradas
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> findAll() {
		Hashtable<Integer, Object> list = formaDePagamentoService.findAll();
		return ResponseEntity.ok().body(list);
	}
}
