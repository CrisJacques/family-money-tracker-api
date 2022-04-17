package com.cristhiane.familymoneytrackerapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cristhiane.familymoneytrackerapi.domain.CategoriaTransacao;

@Repository
public interface CategoriaTransacaoRepository extends JpaRepository<CategoriaTransacao, Integer> {

}
