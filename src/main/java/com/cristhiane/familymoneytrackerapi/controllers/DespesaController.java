package com.cristhiane.familymoneytrackerapi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cristhiane.familymoneytrackerapi.dto.DespesaDTO;
import com.cristhiane.familymoneytrackerapi.service.TransacaoService;

@RestController
@RequestMapping(value = "/api/despesas")
public class DespesaController {
	
	@Autowired
	TransacaoService service;
	
	@RequestMapping(value = "/recentes", method = RequestMethod.GET)
	public ResponseEntity<?> findRecentExpenses(){
		List<DespesaDTO> obj = service.listRecentExpenses();
		
		return ResponseEntity.ok().body(obj);
	}
}
