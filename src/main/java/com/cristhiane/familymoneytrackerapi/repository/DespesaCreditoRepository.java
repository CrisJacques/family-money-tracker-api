package com.cristhiane.familymoneytrackerapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cristhiane.familymoneytrackerapi.domain.DespesaCredito;

@Repository
public interface DespesaCreditoRepository extends JpaRepository<DespesaCredito, Integer> {

}
