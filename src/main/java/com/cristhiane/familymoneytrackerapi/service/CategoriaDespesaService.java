package com.cristhiane.familymoneytrackerapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.cristhiane.familymoneytrackerapi.domain.CategoriaDespesa;
import com.cristhiane.familymoneytrackerapi.repository.CategoriaDespesaRepository;
import com.cristhiane.familymoneytrackerapi.service.exceptions.DataIntegrityException;
import com.cristhiane.familymoneytrackerapi.service.exceptions.ObjetoNaoEncontradoException;

/**
 * Classe que contém os services relacionados à entity Categoria de Despesa
 *
 */
@Service
public class CategoriaDespesaService {
	@Autowired
	private CategoriaDespesaRepository repo;

	/**
	 * Busca pela categoria de despesa cujo id é passado por parâmetro
	 * 
	 * @param id - Id da categoria de despesa
	 * @return Informações da categoria de despesa solicitada
	 * @throws ObjetoNaoEncontradoException Caso não encontre nenhuma categoria de
	 *                                      despesa com o id informado
	 */
	public CategoriaDespesa find(Integer id) {
		Optional<CategoriaDespesa> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjetoNaoEncontradoException(
				"Objeto não encontrado. ID: " + id + ", Tipo: " + CategoriaDespesa.class.getName()));
	}

	/**
	 * Cadastra uma categoria de despesa
	 * 
	 * @param obj - Objeto com as informações da categoria de despesa a ser
	 *            cadastrada
	 * @return Informações da categoria de despesa recém cadastrada
	 */
	public CategoriaDespesa insert(CategoriaDespesa obj) {
		obj.setId(null); // garantindo que o id do objeto seja nulo para que ele seja inserido no banco
							// de dados
		return repo.save(obj);
	}

	/**
	 * Atualiza as informações de uma categoria de despesa
	 * 
	 * @param obj - Objeto com as novas informações da categoria de despesa
	 * @return Informações da categoria de despesa recém atualizada
	 */
	public CategoriaDespesa update(CategoriaDespesa obj) {
		find(obj.getId()); // verificando se o registro existe antes de atualizá-lo
		return repo.save(obj);
	}

	/**
	 * Remove uma categoria de despesa
	 * 
	 * @param id - Id da categoria de despesa a ser removida
	 * @throws DataIntegrityException Caso a categoria de despesa não possa ser
	 *                                deletada, pois isso afetaria a integridade dos
	 *                                dados
	 */
	public void delete(Integer id) {
		find(id); // verificando se o objeto existe antes de deletar
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {// Exceção lançada se a exclusão do objeto afetar a integridade de
														// dados do banco
			throw new DataIntegrityException("Categoria de despesa não pode ser deletada!");
		}
	}

	/**
	 * Busca por todas as categorias de despesa cadastradas
	 * 
	 * @return Lista de todas as categorias de despesa cadastradas
	 */
	public List<CategoriaDespesa> findAll() {
		return repo.findAll();
	}

}
