package com.cristhiane.familymoneytrackerapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.cristhiane.familymoneytrackerapi.domain.CartaoDeCredito;
import com.cristhiane.familymoneytrackerapi.repository.CartaoDeCreditoRepository;
import com.cristhiane.familymoneytrackerapi.service.exceptions.DataIntegrityException;
import com.cristhiane.familymoneytrackerapi.service.exceptions.ObjetoNaoEncontradoException;

/**
 * Classe que contém os services relacionados à entity Cartão de Crédito
 *
 */
@Service
public class CartaoDeCreditoService {
	@Autowired
	private CartaoDeCreditoRepository repo;

	/**
	 * Busca pelo cartão de crédito cujo id é passado por parâmetro
	 * 
	 * @param id - Id do cartão de crédito
	 * @return Informações do cartão de crédito solicitado
	 * @throws ObjetoNaoEncontradoException Caso não encontre nenhum cartão de
	 *                                      crédito com o id informado
	 */
	public CartaoDeCredito find(Integer id) {
		Optional<CartaoDeCredito> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjetoNaoEncontradoException(
				"Objeto não encontrado. ID: " + id + ", Tipo: " + CartaoDeCredito.class.getName()));
	}

	/**
	 * Cadastra um cartão de crédito
	 * 
	 * @param obj - Objeto com as informações do cartão de crédito a ser cadastrado
	 * @return Informações do cartão de crédito recém cadastrado
	 */
	public CartaoDeCredito insert(CartaoDeCredito obj) {
		obj.setId(null); // garantindo que o id do objeto seja nulo para que ele seja inserido no banco
							// de dados
		return repo.save(obj);
	}

	/**
	 * Atualiza as informações de um cartão de crédito
	 * 
	 * @param obj - Objeto com as novas informações do cartão de crédito
	 * @return Informações do cartão de crédito recém atualizado
	 */
	public CartaoDeCredito update(CartaoDeCredito obj) {
		find(obj.getId()); // verificando se o registro existe antes de atualizá-lo
		return repo.save(obj);
	}

	/**
	 * Remove um cartão de crédito
	 * 
	 * @param id - Id do cartão de crédito a ser removido
	 * @throws DataIntegrityException Caso o cartão de crédito não possa ser
	 *                                deletado, pois isso afetaria a integridade dos
	 *                                dados
	 */
	public void delete(Integer id) {
		find(id); // verificando se o objeto existe antes de deletar
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {// Exceção lançada se a exclusão do objeto afetar a integridade de
														// dados do banco
			throw new DataIntegrityException("Cartão de crédito não pode ser deletado!");
		}
	}

	/**
	 * Busca por todos os cartões de crédito cadastrados
	 * 
	 * @return Lista de todos os cartões de crédito cadastrados
	 */
	public List<CartaoDeCredito> findAll() {
		return repo.findAll();
	}

}
