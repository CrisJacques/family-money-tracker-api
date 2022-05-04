package com.cristhiane.familymoneytrackerapi.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cristhiane.familymoneytrackerapi.domain.CategoriaDespesa;
import com.cristhiane.familymoneytrackerapi.domain.DespesaCredito;

@Repository
public interface DespesaCreditoRepository extends JpaRepository<DespesaCredito, Integer> {
	List<DespesaCredito> findByCategoriaDespesa(CategoriaDespesa categoriaDespesa);
	
	List<DespesaCredito> findAllByDataBetween(Date timeStart, Date timeEnd);
	
	List<DespesaCredito> findByCategoriaDespesaAndDataBetween(CategoriaDespesa categoriaDespesa, Date timeStart, Date timeEnd);

}
