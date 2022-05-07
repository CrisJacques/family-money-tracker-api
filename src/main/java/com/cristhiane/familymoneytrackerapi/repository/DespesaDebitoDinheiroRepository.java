package com.cristhiane.familymoneytrackerapi.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cristhiane.familymoneytrackerapi.domain.CategoriaDespesa;
import com.cristhiane.familymoneytrackerapi.domain.DespesaDebitoDinheiro;

@Repository
public interface DespesaDebitoDinheiroRepository extends JpaRepository<DespesaDebitoDinheiro, Integer> {
	List<DespesaDebitoDinheiro> findByCategoriaDespesa(CategoriaDespesa categoriaDespesa);
	
	List<DespesaDebitoDinheiro> findAllByDataBetween(LocalDate timeStart, LocalDate timeEnd);
	
	List<DespesaDebitoDinheiro> findByCategoriaDespesaAndDataBetween(CategoriaDespesa categoriaDespesa, LocalDate timeStart, LocalDate timeEnd);
}
