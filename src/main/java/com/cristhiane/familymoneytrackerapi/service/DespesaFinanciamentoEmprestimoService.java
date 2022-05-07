package com.cristhiane.familymoneytrackerapi.service;

import java.time.LocalDate;
import java.util.Hashtable;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.cristhiane.familymoneytrackerapi.domain.CategoriaDespesa;
import com.cristhiane.familymoneytrackerapi.domain.DespesaFinanciamentoEmprestimo;
import com.cristhiane.familymoneytrackerapi.dto.DespesaDTO;
import com.cristhiane.familymoneytrackerapi.repository.DespesaFinanciamentoEmprestimoRepository;
import com.cristhiane.familymoneytrackerapi.service.exceptions.DataIntegrityException;
import com.cristhiane.familymoneytrackerapi.service.exceptions.ObjetoNaoEncontradoException;
import com.cristhiane.familymoneytrackerapi.utils.DefaultPeriodOfSearch;

@Service
public class DespesaFinanciamentoEmprestimoService {
	@Autowired
	private DespesaFinanciamentoEmprestimoRepository repo;
	
	@Autowired
	private CategoriaDespesaService categoriaDespesaService;
	
	public Hashtable<String, List<DespesaDTO>> findFinancingExpensesByCategoryAndByPeriod(LocalDate timeStart, LocalDate timeEnd) {
		Hashtable<String, List<DespesaDTO>> despesasPorCategoria = new Hashtable<String, List<DespesaDTO>>();
		
		List<CategoriaDespesa> listaCategoriaDespesa = categoriaDespesaService.findAll();
		
		for(CategoriaDespesa categoriaDespesa : listaCategoriaDespesa) {
			List<DespesaFinanciamentoEmprestimo> listFinancingExpenses = repo.findByCategoriaDespesaAndDataBetween(categoriaDespesa, timeStart, timeEnd);
			List<DespesaDTO> listFinancingExpensesDTO = listFinancingExpenses.stream().map(obj -> new DespesaDTO(obj)).collect(Collectors.toList());
			
			despesasPorCategoria.put(categoriaDespesa.getNome(), listFinancingExpensesDTO);

		}
		return despesasPorCategoria;
	}
	
	public List<DespesaDTO> findRecentExpensesFinancingLoan() {
		LocalDate startLocalDate = DefaultPeriodOfSearch.setStartOfPeriod();
		LocalDate endLocalDate = DefaultPeriodOfSearch.setEndOfPeriod();
		
		List<DespesaFinanciamentoEmprestimo> list = repo.findAllByDataBetween(startLocalDate, endLocalDate);
		
		return list.stream().map(obj -> new DespesaDTO(obj)).collect(Collectors.toList());
	}
	
	public DespesaFinanciamentoEmprestimo find(Integer id) {
		Optional<DespesaFinanciamentoEmprestimo> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjetoNaoEncontradoException("Objeto não encontrado. ID: " + id + ", Tipo: " + DespesaFinanciamentoEmprestimo.class.getName()));
	}
	
	public DespesaFinanciamentoEmprestimo insert(DespesaFinanciamentoEmprestimo obj) {
		obj.setId(null); // garantindo que o id do objeto seja nulo para que ele seja inserido no banco de dados
		return repo.save(obj);
	}
	
	public DespesaFinanciamentoEmprestimo update(DespesaFinanciamentoEmprestimo obj) {
		find(obj.getId()); // verificando se o registro existe antes de atualizá-lo
		return repo.save(obj);
	}
	
	public void delete(Integer id) {
		find(id); // verificando se o objeto existe antes de deletar
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {// Exceção lançada se a exclusão do objeto afetar a integridade de dados do banco
			throw new DataIntegrityException("Despesa não pode ser deletada!");
		}
	}
	
	public List<DespesaFinanciamentoEmprestimo> findAll() {
		return repo.findAll();
	}

}
