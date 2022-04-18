package com.cristhiane.familymoneytrackerapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cristhiane.familymoneytrackerapi.domain.DespesaDebitoDinheiro;

@Repository
public interface DespesaDebitoDinheiroRepository extends JpaRepository<DespesaDebitoDinheiro, Integer> {

}
