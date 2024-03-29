package com.cristhiane.familymoneytrackerapi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cristhiane.familymoneytrackerapi.domain.User;

/**
 * Repository que encapsula as interações com o banco de dados para a entidade
 * Usuário
 *
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByUsername(String username);

	Optional<User> findByEmail(String email);
	
	Optional<User> findById(Integer id);

	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);
}