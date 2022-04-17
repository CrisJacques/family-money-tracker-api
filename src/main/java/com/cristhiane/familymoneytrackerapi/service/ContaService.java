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

@Service
public class ContaService {
	@Autowired
	private ContaRepository repo;
	
	public Conta find(Integer id) {
		Optional<Conta> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjetoNaoEncontradoException("Objeto não encontrado. ID: " + id + ", Tipo: " + Conta.class.getName()));
	}
	
	public Conta insert(Conta obj) {
		obj.setId(null); // garantindo que o id do objeto seja nulo para que ele seja inserido no banco de dados
		return repo.save(obj);
	}
	
	public Conta update(Conta obj) {
		find(obj.getId()); // verificando se o registro existe antes de atualizá-lo
		return repo.save(obj);
	}
	
	public void delete(Integer id) {
		find(id); // verificando se o objeto existe antes de deletar
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {// Exceção lançada se a exclusão do objeto afetar a integridade de dados do banco
			throw new DataIntegrityException("Conta não pode ser deletada!");
		}
	}
	
	public List<Conta> findAll() {
		return repo.findAll();
	}

}
