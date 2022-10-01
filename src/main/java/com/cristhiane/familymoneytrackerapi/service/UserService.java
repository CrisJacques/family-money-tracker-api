package com.cristhiane.familymoneytrackerapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cristhiane.familymoneytrackerapi.domain.User;
import com.cristhiane.familymoneytrackerapi.repository.UserRepository;
import com.cristhiane.familymoneytrackerapi.service.exceptions.ObjetoNaoEncontradoException;

/**
 * Classe que contém os services relacionados à entity Usuário
 *
 */
@Service
public class UserService {
	@Autowired
	UserRepository repo;

	/**
	 * Cadastra um usuário
	 * 
	 * @param obj - Objeto com as informações do usuário a ser cadastrado
	 * @return Informações do usuário recém cadastrado
	 */
	public User insert(User obj) {
		obj.setId(null); // garantindo que o id do objeto seja nulo para que ele seja inserido no banco
		// de dados
		return repo.save(obj);
	}

	/**
	 * Busca pelo usuário cujo id é passado por parâmetro
	 * 
	 * @param id - Id do usuário
	 * @return Informações do usuário solicitado
	 * @throws ObjetoNaoEncontradoException Caso não encontre nenhum usuário com o
	 *                                      id informado
	 */
	public User find(Integer id) {
		Optional<User> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjetoNaoEncontradoException(
				"Objeto não encontrado. ID: " + id + ", Tipo: " + User.class.getName()));
	}

	/**
	 * Busca por todos os usuários cadastrados
	 * 
	 * @return Lista de todos os usuários cadastrados
	 */
	public List<User> findAll() {
		return repo.findAll();
	}

}
