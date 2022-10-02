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
		obj.setPassword(encoder.encode(obj.getPassword()));// é preciso rodar o encode no password antes de salvar no
															// banco de dados
		return repo.save(obj);
	}

	/**
	 * Verifica se o nome de usuário já existe no banco de dados
	 * 
	 * @param username - Nome de usuário a ser buscado no banco de dados
	 * @return true se usuário já existe no banco de dados e false caso contrário
	 */
	public Boolean checkIfNameAlreadyExists(String username) {
		return repo.existsByUsername(username);
	}

	/**
	 * Verifica se o email do usuário já existe no banco de dados
	 * 
	 * @param email - Email a ser buscado no banco de dados
	 * @return true se email já existe no banco de dados e false caso contrário
	 */
	public Boolean checkIfEmailAlreadyExists(String email) {
		return repo.existsByEmail(email);
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
