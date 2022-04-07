package com.cristhiane.familymoneytrackerapi.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.cristhiane.familymoneytrackerapi.models.ERole;
import com.cristhiane.familymoneytrackerapi.models.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	Optional<Role> findByName(ERole name);
}
