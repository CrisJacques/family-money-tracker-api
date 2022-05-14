package com.cristhiane.familymoneytrackerapi.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cristhiane.familymoneytrackerapi.domain.Role;
import com.cristhiane.familymoneytrackerapi.enums.TipoUsuario;

/**
 * Repository que encapsula as interações com o banco de dados para a entidade
 * Role (Perfis de usuário)
 *
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	Optional<Role> findByName(TipoUsuario name);
}
