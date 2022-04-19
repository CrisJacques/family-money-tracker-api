package com.cristhiane.familymoneytrackerapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cristhiane.familymoneytrackerapi.domain.DespesaFinanciamentoEmprestimo;

@Repository
public interface DespesaFinanciamentoEmprestimoRepository extends JpaRepository<DespesaFinanciamentoEmprestimo, Integer> {

}
