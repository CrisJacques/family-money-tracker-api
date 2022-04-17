package com.cristhiane.familymoneytrackerapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cristhiane.familymoneytrackerapi.domain.CartaoDeCredito;

@Repository
public interface CartaoDeCreditoRepository extends JpaRepository<CartaoDeCredito, Integer> {

}
