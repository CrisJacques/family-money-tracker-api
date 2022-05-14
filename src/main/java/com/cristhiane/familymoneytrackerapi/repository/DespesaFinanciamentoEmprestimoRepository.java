package com.cristhiane.familymoneytrackerapi.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cristhiane.familymoneytrackerapi.domain.CategoriaDespesa;
import com.cristhiane.familymoneytrackerapi.domain.DespesaFinanciamentoEmprestimo;

/**
 * Repository que encapsula as interações com o banco de dados para a entidade
 * Despesa cuja forma de pagamento é Financiamento ou Empréstimo
 *
 */
@Repository
public interface DespesaFinanciamentoEmprestimoRepository
		extends JpaRepository<DespesaFinanciamentoEmprestimo, Integer> {
	List<DespesaFinanciamentoEmprestimo> findByCategoriaDespesa(CategoriaDespesa categoriaDespesa);

	List<DespesaFinanciamentoEmprestimo> findAllByDataBetween(LocalDate timeStart, LocalDate timeEnd);

	List<DespesaFinanciamentoEmprestimo> findByCategoriaDespesaAndDataBetween(CategoriaDespesa categoriaDespesa,
			LocalDate timeStart, LocalDate timeEnd);
}
