package com.cristhiane.familymoneytrackerapi.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.cristhiane.familymoneytrackerapi.domain.CategoriaDespesa;
import com.cristhiane.familymoneytrackerapi.domain.CategoriaReceita;
import com.cristhiane.familymoneytrackerapi.service.CategoriaReceitaService;

@RestController
@RequestMapping(value = "/api/categorias-receitas")
public class CategoriaReceitaController {
	
	@Autowired
	CategoriaReceitaService service;
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id){
		CategoriaReceita obj = service.find(id);
		
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> insert(@RequestBody CategoriaReceita obj){
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();// criando URI para o recurso recém criado
		
		return ResponseEntity.created(uri).build();// retorna status code 201 e no cabeçalho da resposta informa a uri do recurso recém criado
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody CategoriaReceita obj, @PathVariable Integer id){
		obj.setId(id);
		obj = service.update(obj);
		
		return ResponseEntity.noContent().build();// retorna status code 204
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id){
		service.delete(id);
		
		return ResponseEntity.noContent().build();// retorna status code 204
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<CategoriaReceita>> findAll() {
		List<CategoriaReceita> list = service.findAll(); 
		return ResponseEntity.ok().body(list);
	}
}