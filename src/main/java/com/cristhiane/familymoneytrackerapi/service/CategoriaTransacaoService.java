package com.cristhiane.familymoneytrackerapi.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.cristhiane.familymoneytrackerapi.domain.CategoriaTransacao;
import com.cristhiane.familymoneytrackerapi.repository.CategoriaTransacaoRepository;
import com.cristhiane.familymoneytrackerapi.service.exceptions.DataIntegrityException;
import com.cristhiane.familymoneytrackerapi.service.exceptions.ObjetoNaoEncontradoException;

@Service
public class CategoriaTransacaoService {
	@Autowired
	private CategoriaTransacaoRepository repo;
	
	public CategoriaTransacao find(Integer id) {
		Optional<CategoriaTransacao> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjetoNaoEncontradoException("Objeto não encontrado. ID: " + id + ", Tipo: " + CategoriaTransacao.class.getName()));
	}
	
	public CategoriaTransacao insert(CategoriaTransacao obj) {
		obj.setId(null); // garantindo que o id do objeto seja nulo para que ele seja inserido no banco de dados
		return repo.save(obj);
	}
	
	public CategoriaTransacao update(CategoriaTransacao obj) {
		find(obj.getId()); // verificando se o registro existe antes de atualizá-lo
		return repo.save(obj);
	}
	
	public void delete(Integer id) {
		find(id); // verificando se o objeto existe antes de deletar
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {// Exceção lançada se a exclusão do objeto afetar a integridade de dados do banco
			throw new DataIntegrityException("Categoria de transação não pode ser deletada!");
		}
	}

}
