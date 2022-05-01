package com.cristhiane.familymoneytrackerapi.service;

import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.cristhiane.familymoneytrackerapi.domain.CategoriaReceita;
import com.cristhiane.familymoneytrackerapi.domain.Receita;
import com.cristhiane.familymoneytrackerapi.dto.ReceitaDTO;
import com.cristhiane.familymoneytrackerapi.repository.ReceitaRepository;
import com.cristhiane.familymoneytrackerapi.service.exceptions.DataIntegrityException;
import com.cristhiane.familymoneytrackerapi.service.exceptions.ObjetoNaoEncontradoException;
import com.cristhiane.familymoneytrackerapi.utils.DefaultPeriodOfSearch;

@Service
public class ReceitaService {
	@Autowired
	private ReceitaRepository repo;
	
	@Autowired
	private CategoriaReceitaService categoriaReceitaService;
	
	private Pageable fiveMostRecent = PageRequest.of(0, 5, Sort.by("data").descending());
	
	public Hashtable<String, List<ReceitaDTO>> findIncomesByCategoryAndByPeriod(Date timeStart, Date timeEnd) {
		Hashtable<String, List<ReceitaDTO>> receitasPorCategoria = new Hashtable<String, List<ReceitaDTO>>();
		
		List<CategoriaReceita> listaCategoriaReceita = categoriaReceitaService.findAll();
		
		for(CategoriaReceita categoriaReceita : listaCategoriaReceita) {
			List<Receita> listIncomes = repo.findByCategoriaReceitaAndDataBetween(categoriaReceita, timeStart, timeEnd);
			List<ReceitaDTO> listIncomesDTO = listIncomes.stream().map(obj -> new ReceitaDTO(obj)).collect(Collectors.toList());
			
			receitasPorCategoria.put(categoriaReceita.getNome(), listIncomesDTO);

		}
		return receitasPorCategoria;
	}

	
	
	public Hashtable<String, Object> calculateSumIncomesByCategoryAndByPeriod(Date timeStart, Date timeEnd) {
		Hashtable<String, Object> totalIncomesByCategory = new Hashtable<String, Object>();

		Hashtable<String, List<ReceitaDTO>> receitasPorCategoria = this.findIncomesByCategoryAndByPeriod(timeStart, timeEnd);

		Set<String> setOfKeys = receitasPorCategoria.keySet();

		for (String key : setOfKeys) {
			float sumIncomesByCategory = 0;
			List<ReceitaDTO> listaReceitas = receitasPorCategoria.get(key);
			for (ReceitaDTO receita : listaReceitas) {
				sumIncomesByCategory = sumIncomesByCategory + receita.getValor();
			}
			totalIncomesByCategory.put(key, sumIncomesByCategory);
		}
		return totalIncomesByCategory;
	}
	 
	
	public List<ReceitaDTO> findRecentIncomes() {
		Date startDate = DefaultPeriodOfSearch.setStartOfPeriod();
		Date endDate = DefaultPeriodOfSearch.setEndOfPeriod();
		
		List<Receita> list = repo.findAllByDataBetween(startDate, endDate);
		return list.stream().map(obj -> new ReceitaDTO(obj)).collect(Collectors.toList());
	}
	
	public Receita find(Integer id) {
		Optional<Receita> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjetoNaoEncontradoException("Objeto não encontrado. ID: " + id + ", Tipo: " + Receita.class.getName()));
	}
	
	public Receita insert(Receita obj) {
		obj.setId(null); // garantindo que o id do objeto seja nulo para que ele seja inserido no banco de dados
		return repo.save(obj);
	}
	
	public Receita update(Receita obj) {
		find(obj.getId()); // verificando se o registro existe antes de atualizá-lo
		return repo.save(obj);
	}
	
	public void delete(Integer id) {
		find(id); // verificando se o objeto existe antes de deletar
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {// Exceção lançada se a exclusão do objeto afetar a integridade de dados do banco
			throw new DataIntegrityException("Receita não pode ser deletada!");
		}
	}
	
	public List<ReceitaDTO> findAll() {
		List<Receita> list = repo.findAll();
		return list.stream().map(obj -> new ReceitaDTO(obj)).collect(Collectors.toList());
	}

}
