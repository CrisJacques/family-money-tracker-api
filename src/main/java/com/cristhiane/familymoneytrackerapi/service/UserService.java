package com.cristhiane.familymoneytrackerapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cristhiane.familymoneytrackerapi.domain.User;
import com.cristhiane.familymoneytrackerapi.repository.UserRepository;

/**
 * Classe que contém os services relacionados à entity Usuário
 *
 */
@Service
public class UserService {
	@Autowired
	UserRepository repo;
	
	@Autowired
	PasswordEncoder encoder;

	/**
	 * Cadastra um usuário
	 * 
	 * @param obj - Objeto com as informações do usuário a ser cadastrado
	 * @return Informações do usuário recém cadastrado
	 */
	public User insert(User obj) {
		obj.setId(null); // garantindo que o id do objeto seja nulo para que ele seja inserido no banco
		// de dados
		obj.setPassword(encoder.encode(obj.getPassword()));// é preciso rodar o encode no password antes de salvar no banco de dados
		return repo.save(obj);
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
