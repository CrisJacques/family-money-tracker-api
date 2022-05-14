package com.cristhiane.familymoneytrackerapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cristhiane.familymoneytrackerapi.domain.CategoriaDespesa;

/**
 * Repository que encapsula as interações com o banco de dados para a entidade
 * Categoria de Despesa
 *
 */
@Repository
public interface CategoriaDespesaRepository extends JpaRepository<CategoriaDespesa, Integer> {

}
