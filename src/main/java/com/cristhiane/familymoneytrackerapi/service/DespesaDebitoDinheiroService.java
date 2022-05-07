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
import com.cristhiane.familymoneytrackerapi.domain.DespesaDebitoDinheiro;
import com.cristhiane.familymoneytrackerapi.dto.DespesaDTO;
import com.cristhiane.familymoneytrackerapi.repository.DespesaDebitoDinheiroRepository;
import com.cristhiane.familymoneytrackerapi.service.exceptions.DataIntegrityException;
import com.cristhiane.familymoneytrackerapi.service.exceptions.ObjetoNaoEncontradoException;
import com.cristhiane.familymoneytrackerapi.utils.DefaultPeriodOfSearch;

@Service
public class DespesaDebitoDinheiroService {
	@Autowired
	private DespesaDebitoDinheiroRepository repo;
	
	@Autowired
	private CategoriaDespesaService categoriaDespesaService;
	
	public Hashtable<String, List<DespesaDTO>> findDebitExpensesByCategoryAndByPeriod(LocalDate timeStart, LocalDate timeEnd) {
		Hashtable<String, List<DespesaDTO>> despesasPorCategoria = new Hashtable<String, List<DespesaDTO>>();
		
		List<CategoriaDespesa> listaCategoriaDespesa = categoriaDespesaService.findAll();
		
		for(CategoriaDespesa categoriaDespesa : listaCategoriaDespesa) {
			List<DespesaDebitoDinheiro> listDebitExpenses = repo.findByCategoriaDespesaAndDataBetween(categoriaDespesa, timeStart, timeEnd);
			List<DespesaDTO> listDebitExpensesDTO = listDebitExpenses.stream().map(obj -> new DespesaDTO(obj)).collect(Collectors.toList());
			
			despesasPorCategoria.put(categoriaDespesa.getNome(), listDebitExpensesDTO);

		}
		return despesasPorCategoria;
	}
	
	public List<DespesaDTO> findRecentExpensesDebitCash() {
		LocalDate startLocalDate = DefaultPeriodOfSearch.setStartOfPeriod();
		LocalDate endLocalDate = DefaultPeriodOfSearch.setEndOfPeriod();
		
		List<DespesaDebitoDinheiro> list = repo.findAllByDataBetween(startLocalDate, endLocalDate);
		
		return list.stream().map(obj -> new DespesaDTO(obj)).collect(Collectors.toList());
	}
	
	public DespesaDebitoDinheiro find(Integer id) {
		Optional<DespesaDebitoDinheiro> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjetoNaoEncontradoException("Objeto não encontrado. ID: " + id + ", Tipo: " + DespesaDebitoDinheiro.class.getName()));
	}
	
	public DespesaDebitoDinheiro insert(DespesaDebitoDinheiro obj) {
		obj.setId(null); // garantindo que o id do objeto seja nulo para que ele seja inserido no banco de dados
		return repo.save(obj);
	}
	
	public DespesaDebitoDinheiro update(DespesaDebitoDinheiro obj) {
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
	
	public List<DespesaDebitoDinheiro> findAll() {
		return repo.findAll();
	}

}
