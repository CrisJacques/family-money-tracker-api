package com.cristhiane.familymoneytrackerapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cristhiane.familymoneytrackerapi.domain.CategoriaReceita;

@Repository
public interface CategoriaReceitaRepository extends JpaRepository<CategoriaReceita, Integer> {

}
