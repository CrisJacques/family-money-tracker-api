package com.cristhiane.familymoneytrackerapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cristhiane.familymoneytrackerapi.domain.CartaoDeCredito;

/**
 * Repository que encapsula as interações com o banco de dados para a entidade
 * Cartão de Crédito
 *
 */
@Repository
public interface CartaoDeCreditoRepository extends JpaRepository<CartaoDeCredito, Integer> {

}
