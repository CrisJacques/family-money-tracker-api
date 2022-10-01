package com.cristhiane.familymoneytrackerapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cristhiane.familymoneytrackerapi.domain.GrupoUsuarios;
import com.cristhiane.familymoneytrackerapi.repository.GrupoUsuariosRepository;

/**
 * Classe que contém os services relacionados à entity Grupo de usuário
 *
 */
@Service
public class GrupoUsuariosService {
	@Autowired
	GrupoUsuariosRepository repo;

	/**
	 * Cadastra um grupo de usuários
	 * 
	 * @param obj - Objeto com as informações do grupo de usuários a ser cadastrado
	 * @return Informações do grupo de usuário recém cadastrado
	 */
	public GrupoUsuarios insert(GrupoUsuarios obj) {
		obj.setId(null); // garantindo que o id do objeto seja nulo para que ele seja inserido no banco
		// de dados
		return repo.save(obj);
	}

	/**
	 * Busca por todos os grupos de usuários cadastrados
	 * 
	 * @return Lista de todos os grupos de usuários cadastrados
	 */
	public List<GrupoUsuarios> findAll() {
		return repo.findAll();
	}

}
