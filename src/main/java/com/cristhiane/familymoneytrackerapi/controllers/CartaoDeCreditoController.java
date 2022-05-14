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

import com.cristhiane.familymoneytrackerapi.domain.CartaoDeCredito;
import com.cristhiane.familymoneytrackerapi.service.CartaoDeCreditoService;

/**
 * Controller responsável pelas requisições relacionadas a cartões de crédito
 *
 */
@RestController
@RequestMapping(value = "/api/cartoes-de-credito")
public class CartaoDeCreditoController {

	@Autowired
	CartaoDeCreditoService service;

	/**
	 * Busca por um cartão de crédito cujo id é passado por parâmetro
	 * 
	 * @param id - Id do cartão de crédito
	 * @return Informações do cartão de crédito solicitado
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id) {
		CartaoDeCredito obj = service.find(id);

		return ResponseEntity.ok().body(obj);
	}

	/**
	 * Insere um cartão de crédito
	 * 
	 * @param obj - Corpo da requisição
	 * @return Caso inserção ocorra com sucesso, retorna a uri do recurso recém
	 *         criado no cabeçalho da resposta
	 */
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> insert(@RequestBody CartaoDeCredito obj) {
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();// criando
																														// URI
																														// para
																														// o
																														// recurso
																														// recém
																														// criado

		return ResponseEntity.created(uri).build();// retorna status code 201 e no cabeçalho da resposta informa a uri
													// do recurso recém criado
	}

	/**
	 * Atualiza as informações de um cartão de crédito
	 * 
	 * @param obj - Corpo da requisição
	 * @param id  - Id do cartão de crédito que deve ser atualizado
	 * @return Status code 204 se atualização ocorreu com sucesso (sem corpo na
	 *         resposta)
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody CartaoDeCredito obj, @PathVariable Integer id) {
		obj.setId(id);
		obj = service.update(obj);

		return ResponseEntity.noContent().build();// retorna status code 204
	}

	/**
	 * Remove um cartão de crédito
	 * 
	 * @param id - Id do cartão de crédito que deve ser removido
	 * @return Status code 204 se remoção ocorreu com sucesso (sem corpo na
	 *         resposta)
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);

		return ResponseEntity.noContent().build();// retorna status code 204
	}

	/**
	 * Busca por todos os cartões de crédito cadastrados
	 * 
	 * @return Lista de todos os cartões de crédito cadastrados
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<CartaoDeCredito>> findAll() {
		List<CartaoDeCredito> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
}
