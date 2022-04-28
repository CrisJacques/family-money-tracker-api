package com.cristhiane.familymoneytrackerapi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cristhiane.familymoneytrackerapi.service.TransacaoService;

@RestController
@RequestMapping(value = "/api/transacoes-recentes")
public class TransacaoController {
	
	@Autowired
	TransacaoService service;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<?>> findRecentTransactions() {
		List<?> list = service.listRecentTransactions();
		return ResponseEntity.ok().body(list);
	}

}
