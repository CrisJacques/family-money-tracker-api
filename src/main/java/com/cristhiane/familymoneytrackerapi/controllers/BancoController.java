package com.cristhiane.familymoneytrackerapi.controllers;

import java.util.Hashtable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cristhiane.familymoneytrackerapi.service.BancoService;

/**
 * Controller responsável pelas requisições relacionadas a bancos
 *
 */
@RestController
@RequestMapping(value = "/api/bancos")
public class BancoController {

	@Autowired
	BancoService bancoService;

	/**
	 * Busca por todos os bancos cadastrados
	 * 
	 * @return Lista de bancos cadastrados
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> findAll() {
		Hashtable<Integer, Object> list = bancoService.findAll();
		return ResponseEntity.ok().body(list);
	}
}
