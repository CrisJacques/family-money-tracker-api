package com.cristhiane.familymoneytrackerapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.cristhiane.familymoneytrackerapi.domain.CategoriaReceita;
import com.cristhiane.familymoneytrackerapi.repository.CategoriaReceitaRepository;
import com.cristhiane.familymoneytrackerapi.service.exceptions.DataIntegrityException;
import com.cristhiane.familymoneytrackerapi.service.exceptions.ObjetoNaoEncontradoException;

/**
 * Classe que contém os services relacionados à entity Categoria de Receita
 *
 */
@Service
public class CategoriaReceitaService {
	@Autowired
	private CategoriaReceitaRepository repo;

	/**
	 * Busca pela categoria de receita cujo id é passado por parâmetro
	 * 
	 * @param id - Id da categoria de receita
	 * @return Informações da categoria de receita solicitada
	 * @throws ObjetoNaoEncontradoException Caso não encontre nenhuma categoria de
	 *                                      receita com o id informado
	 */
	public CategoriaReceita find(Integer id) {
		Optional<CategoriaReceita> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjetoNaoEncontradoException(
				"Objeto não encontrado. ID: " + id + ", Tipo: " + CategoriaReceita.class.getName()));
	}

	/**
	 * Cadastra uma categoria de receita
	 * 
	 * @param obj - Objeto com as informações da categoria de receita a ser
	 *            cadastrada
	 * @return Informações da categoria de receita recém cadastrada
	 */
	public CategoriaReceita insert(CategoriaReceita obj) {
		obj.setId(null); // garantindo que o id do objeto seja nulo para que ele seja inserido no banco
							// de dados
		return repo.save(obj);
	}

	/**
	 * Atualiza as informações de uma categoria de receita
	 * 
	 * @param obj - Objeto com as novas informações da categoria de receita
	 * @return Informações da categoria de receita recém atualizada
	 */
	public CategoriaReceita update(CategoriaReceita obj) {
		find(obj.getId()); // verificando se o registro existe antes de atualizá-lo
		return repo.save(obj);
	}

	/**
	 * Remove uma categoria de receita
	 * 
	 * @param id - Id da categoria de receita a ser removida
	 * @throws DataIntegrityException Caso a categoria de receita não possa ser
	 *                                deletada, pois isso afetaria a integridade dos
	 *                                dados
	 */
	public void delete(Integer id) {
		find(id); // verificando se o objeto existe antes de deletar
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {// Exceção lançada se a exclusão do objeto afetar a integridade de
														// dados do banco
			throw new DataIntegrityException("Categoria de receita não pode ser deletada!");
		}
	}

	/**
	 * Busca por todas as categorias de receita cadastradas
	 * 
	 * @return Lista de todas as categorias de receita cadastradas
	 */
	public List<CategoriaReceita> findAll() {
		return repo.findAll();
	}

}