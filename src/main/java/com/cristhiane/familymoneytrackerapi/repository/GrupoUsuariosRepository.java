package com.cristhiane.familymoneytrackerapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cristhiane.familymoneytrackerapi.domain.GrupoUsuarios;

@Repository
public interface GrupoUsuariosRepository extends JpaRepository<GrupoUsuarios, Integer> {

}

