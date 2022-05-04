package com.cristhiane.familymoneytrackerapi.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cristhiane.familymoneytrackerapi.domain.CategoriaDespesa;
import com.cristhiane.familymoneytrackerapi.domain.DespesaFinanciamentoEmprestimo;

@Repository
public interface DespesaFinanciamentoEmprestimoRepository extends JpaRepository<DespesaFinanciamentoEmprestimo, Integer> {
	List<DespesaFinanciamentoEmprestimo> findByCategoriaDespesa(CategoriaDespesa categoriaDespesa);
	
	List<DespesaFinanciamentoEmprestimo> findAllByDataBetween(Date timeStart, Date timeEnd);
	
	List<DespesaFinanciamentoEmprestimo> findByCategoriaDespesaAndDataBetween(CategoriaDespesa categoriaDespesa, Date timeStart, Date timeEnd);
}
