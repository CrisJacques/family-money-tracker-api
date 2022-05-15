package com.cristhiane.familymoneytrackerapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.cristhiane.familymoneytrackerapi.domain.Conta;
import com.cristhiane.familymoneytrackerapi.repository.ContaRepository;
import com.cristhiane.familymoneytrackerapi.service.exceptions.DataIntegrityException;
import com.cristhiane.familymoneytrackerapi.service.exceptions.ObjetoNaoEncontradoException;

/**
 * Classe que contém os services relacionados à entity Conta
 *
 */
@Service
public class ContaService {
	@Autowired
	private ContaRepository repo;

	/**
	 * Busca pela conta cujo id é passado por parâmetro
	 * 
	 * @param id - Id da conta
	 * @return Informações da conta solicitada
	 * @throws ObjetoNaoEncontradoException Caso não encontre nenhuma conta com o id
	 *                                      informado
	 */
	public Conta find(Integer id) {
		Optional<Conta> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjetoNaoEncontradoException(
				"Objeto não encontrado. ID: " + id + ", Tipo: " + Conta.class.getName()));
	}

	/**
	 * Cadastra uma conta
	 * 
	 * @param obj - Objeto com as informações da conta a ser cadastrada
	 * @return Informações da conta recém cadastrada
	 */
	public Conta insert(Conta obj) {
		obj.setId(null); // garantindo que o id do objeto seja nulo para que ele seja inserido no banco
							// de dados
		return repo.save(obj);
	}

	/**
	 * Atualiza as informações de uma conta
	 * 
	 * @param obj - Objeto com as novas informações da conta
	 * @return Informações da conta recém atualizada
	 */
	public Conta update(Conta obj) {
		find(obj.getId()); // verificando se o registro existe antes de atualizá-lo
		return repo.save(obj);
	}

	/**
	 * Remove uma conta
	 * 
	 * @param id - Id da conta a ser removida
	 * @throws DataIntegrityException Caso a conta não possa ser deletada, pois isso
	 *                                afetaria a integridade dos dados
	 */
	public void delete(Integer id) {
		find(id); // verificando se o objeto existe antes de deletar
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {// Exceção lançada se a exclusão do objeto afetar a integridade de
														// dados do banco
			throw new DataIntegrityException("Conta não pode ser deletada!");
		}
	}

	/**
	 * Busca por todas as contas cadastradas
	 * 
	 * @return Lista de todas as contas cadastradas
	 */
	public List<Conta> findAll() {
		return repo.findAll();
	}

}
