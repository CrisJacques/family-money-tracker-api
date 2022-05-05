package com.cristhiane.familymoneytrackerapi.service;

import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.cristhiane.familymoneytrackerapi.domain.CategoriaDespesa;
import com.cristhiane.familymoneytrackerapi.domain.DespesaCredito;
import com.cristhiane.familymoneytrackerapi.dto.DespesaDTO;
import com.cristhiane.familymoneytrackerapi.repository.DespesaCreditoRepository;
import com.cristhiane.familymoneytrackerapi.service.exceptions.DataIntegrityException;
import com.cristhiane.familymoneytrackerapi.service.exceptions.ObjetoNaoEncontradoException;
import com.cristhiane.familymoneytrackerapi.utils.DefaultPeriodOfSearch;

@Service
public class DespesaCreditoService {
	@Autowired
	private DespesaCreditoRepository repo;

	@Autowired
	private CategoriaDespesaService categoriaDespesaService;
	
	public Hashtable<String, List<DespesaDTO>> findCreditExpensesByCategoryAndByPeriod(Date timeStart, Date timeEnd) {
		Hashtable<String, List<DespesaDTO>> despesasPorCategoria = new Hashtable<String, List<DespesaDTO>>();
		
		List<CategoriaDespesa> listaCategoriaDespesa = categoriaDespesaService.findAll();
		
		for(CategoriaDespesa categoriaDespesa : listaCategoriaDespesa) {
			List<DespesaCredito> listCreditExpenses = repo.findByCategoriaDespesaAndDataBetween(categoriaDespesa, timeStart, timeEnd);
			List<DespesaDTO> listCreditExpensesDTO = listCreditExpenses.stream().map(obj -> new DespesaDTO(obj)).collect(Collectors.toList());
			
			despesasPorCategoria.put(categoriaDespesa.getNome(), listCreditExpensesDTO);

		}
		return despesasPorCategoria;
	}
	
	public List<DespesaDTO> findRecentExpensesCredit() {
		Date startDate = DefaultPeriodOfSearch.setStartOfPeriod();
		Date endDate = DefaultPeriodOfSearch.setEndOfPeriod();
		
		List<DespesaCredito> list = repo.findAllByDataBetween(startDate, endDate);
		
		return list.stream().map(obj -> new DespesaDTO(obj)).collect(Collectors.toList());
	}
	
	public DespesaCredito find(Integer id) {
		Optional<DespesaCredito> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjetoNaoEncontradoException("Objeto não encontrado. ID: " + id + ", Tipo: " + DespesaCredito.class.getName()));
	}
	
	public DespesaCredito insert(DespesaCredito obj) {
		obj.setId(null); // garantindo que o id do objeto seja nulo para que ele seja inserido no banco de dados
		return repo.save(obj);
	}
	
	public DespesaCredito update(DespesaCredito obj) {
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
	
	public List<DespesaCredito> findAll() {
		return repo.findAll();
	}

}
