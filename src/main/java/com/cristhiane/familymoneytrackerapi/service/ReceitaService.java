package com.cristhiane.familymoneytrackerapi.service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
	
	public Hashtable<String, List<ReceitaDTO>> findIncomesByCategoryAndByPeriod(LocalDate timeStart, LocalDate timeEnd) {
		Hashtable<String, List<ReceitaDTO>> receitasPorCategoria = new Hashtable<String, List<ReceitaDTO>>();
		
		List<CategoriaReceita> listaCategoriaReceita = categoriaReceitaService.findAll();
		
		for(CategoriaReceita categoriaReceita : listaCategoriaReceita) {
			List<Receita> listIncomes = repo.findByCategoriaReceitaAndDataBetween(categoriaReceita, timeStart, timeEnd);
			List<ReceitaDTO> listIncomesDTO = listIncomes.stream().map(obj -> new ReceitaDTO(obj)).collect(Collectors.toList());
			
			receitasPorCategoria.put(categoriaReceita.getNome(), listIncomesDTO);

		}
		return receitasPorCategoria;
	}

	public Hashtable<String, Object> calculateSumIncomesByCategoryAndByPeriod(LocalDate timeStart, LocalDate timeEnd) {
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
	
	public Hashtable<String, Object> calculateSumIncomesByPeriod(LocalDate timeStart, LocalDate timeEnd){
		float sumIncomesByPeriod = 0;
		Hashtable<String, Object> totalIncomesByPeriod = new Hashtable<String, Object>();
		
		Hashtable<String, Object> totalIncomesByCategory = this.calculateSumIncomesByCategoryAndByPeriod(timeStart, timeEnd);
		
		Set<String> setOfKeys = totalIncomesByCategory.keySet();
		
		for (String key : setOfKeys) {
			float actualValue = (float) totalIncomesByCategory.get(key);
			sumIncomesByPeriod = sumIncomesByPeriod + actualValue;
		}
		
		totalIncomesByPeriod.put("Total", sumIncomesByPeriod);
		
		return totalIncomesByPeriod;
	}
	 
	public List<ReceitaDTO> findRecentIncomes() {
		LocalDate startLocalDate = DefaultPeriodOfSearch.setStartOfPeriod();
		LocalDate endLocalDate = DefaultPeriodOfSearch.setEndOfPeriod();
		
		List<Receita> list = repo.findAllByDataBetween(startLocalDate, endLocalDate);
		List<ReceitaDTO> listDTO = list.stream().map(obj -> new ReceitaDTO(obj)).collect(Collectors.toList());
		
		Collections.sort(listDTO, new Comparator<ReceitaDTO>() {
			public int compare(ReceitaDTO one, ReceitaDTO other) {
				return other.getData().compareTo(one.getData()); // fazendo dessa forma os registros são ordenados do mais recente para o mais antigo
			}
		});
		
		List<ReceitaDTO> firstFiveIncomes = listDTO.stream().limit(5).collect(Collectors.toList()); // retorna as 5 receitas mais recentes
		
		return firstFiveIncomes;
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
