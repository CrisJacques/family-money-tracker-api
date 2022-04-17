package com.cristhiane.familymoneytrackerapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cristhiane.familymoneytrackerapi.domain.Receita;

@Repository
public interface ReceitaRepository extends JpaRepository<Receita, Integer> {

}
