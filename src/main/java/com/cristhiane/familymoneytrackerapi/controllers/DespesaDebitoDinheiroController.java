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

import com.cristhiane.familymoneytrackerapi.domain.DespesaDebitoDinheiro;
import com.cristhiane.familymoneytrackerapi.service.DespesaDebitoDinheiroService;

/**
 * Controller responsável pelas requisições relacionadas a despesas cuja forma
 * de pagamento é débito ou dinheiro
 *
 */
@RestController
@RequestMapping(value = "/api/despesas-debito-dinheiro")
public class DespesaDebitoDinheiroController {

	@Autowired
	DespesaDebitoDinheiroService service;

	/**
	 * Busca por uma despesa (pagamento via débito ou dinheiro) cujo id é passado
	 * por parâmetro
	 * 
	 * @param id - Id da despesa
	 * @return Informações da despesa solicitada
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id) {
		DespesaDebitoDinheiro obj = service.find(id);

		return ResponseEntity.ok().body(obj);
	}

	/**
	 * Insere uma despesa (pagamento via débito ou dinheiro)
	 * 
	 * @param obj - Corpo da requisição
	 * @return Caso inserção ocorra com sucesso, retorna a uri do recurso recém
	 *         criado no cabeçalho da resposta
	 */
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> insert(@RequestBody DespesaDebitoDinheiro obj) {
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
	 * Atualiza as informações de uma despesa (pagamento via débito ou dinheiro)
	 * 
	 * @param obj - Corpo da requisição
	 * @param id  - Id da despesa que deve ser atualizada
	 * @return Status code 204 se atualização ocorreu com sucesso (sem corpo na
	 *         resposta)
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody DespesaDebitoDinheiro obj, @PathVariable Integer id) {
		obj.setId(id);
		obj = service.update(obj);

		return ResponseEntity.noContent().build();// retorna status code 204
	}

	/**
	 * Remove uma despesa (pagamento via débito ou dinheiro)
	 * 
	 * @param id - Id da despesa que deve ser removida
	 * @return Status code 204 se remoção ocorreu com sucesso (sem corpo na
	 *         resposta)
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);

		return ResponseEntity.noContent().build();// retorna status code 204
	}

	/**
	 * Busca por todas as despesas cadastradas (pagamento via débito ou dinheiro)
	 * 
	 * @return Lista de todas as despesas cadastradas
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<DespesaDebitoDinheiro>> findAll() {
		List<DespesaDebitoDinheiro> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
}