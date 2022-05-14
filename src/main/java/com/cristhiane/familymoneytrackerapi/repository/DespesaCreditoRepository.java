package com.cristhiane.familymoneytrackerapi.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cristhiane.familymoneytrackerapi.domain.CategoriaDespesa;
import com.cristhiane.familymoneytrackerapi.domain.DespesaCredito;

/**
 * Repository que encapsula as interações com o banco de dados para a entidade
 * Despesa cuja forma de pagamento é Cartão de Crédito
 *
 */
@Repository
public interface DespesaCreditoRepository extends JpaRepository<DespesaCredito, Integer> {
	List<DespesaCredito> findByCategoriaDespesa(CategoriaDespesa categoriaDespesa);

	List<DespesaCredito> findAllByDataBetween(LocalDate timeStart, LocalDate timeEnd);

	List<DespesaCredito> findByCategoriaDespesaAndDataBetween(CategoriaDespesa categoriaDespesa, LocalDate timeStart,
			LocalDate timeEnd);

}
