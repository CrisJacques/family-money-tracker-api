package com.cristhiane.familymoneytrackerapi.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cristhiane.familymoneytrackerapi.domain.CategoriaDespesa;
import com.cristhiane.familymoneytrackerapi.domain.DespesaCredito;
import com.cristhiane.familymoneytrackerapi.domain.DespesaDebitoDinheiro;
import com.cristhiane.familymoneytrackerapi.domain.DespesaFinanciamentoEmprestimo;
import com.cristhiane.familymoneytrackerapi.dto.DespesaDTO;
import com.cristhiane.familymoneytrackerapi.dto.ReceitaDTO;
import com.cristhiane.familymoneytrackerapi.repository.DespesaCreditoRepository;
import com.cristhiane.familymoneytrackerapi.repository.DespesaDebitoDinheiroRepository;
import com.cristhiane.familymoneytrackerapi.repository.DespesaFinanciamentoEmprestimoRepository;

@Service
public class TransacaoService {
	
	@Autowired
	private ReceitaService receitaService;
	
	@Autowired
	DespesaDebitoDinheiroService despesaDebitoDinheiroService;
	
	@Autowired
	DespesaCreditoService despesaCreditoService;

	@Autowired
	DespesaFinanciamentoEmprestimoService despesaFinanciamentoEmprestimoService;
	
	@Autowired
	CategoriaDespesaService categoriaDespesaService;
	
	@Autowired
	DespesaDebitoDinheiroRepository despesaDebitoDinheiroRepository;
	
	@Autowired
	DespesaCreditoRepository despesaCreditoRepository;
	
	@Autowired
	DespesaFinanciamentoEmprestimoRepository despesaFinanciamentoEmprestimoRepository;
	
	public Hashtable<String, List<DespesaDTO>> findExpensesByCategoryAndByPeriod(Date timeStart, Date timeEnd) {
		Hashtable<String, List<DespesaDTO>> despesasPorCategoria = new Hashtable<String, List<DespesaDTO>>();
		
		List<CategoriaDespesa> listaCategoriaDespesa = categoriaDespesaService.findAll();
		
		for(CategoriaDespesa categoriaDespesa : listaCategoriaDespesa) {
			List<DespesaDTO> listExpensesDTO = new ArrayList<>();
			
			List<DespesaDebitoDinheiro> listExpensesDebit = despesaDebitoDinheiroRepository.findByCategoriaDespesaAndDataBetween(categoriaDespesa, timeStart, timeEnd);
			List<DespesaDTO> listExpensesDebitDTO = listExpensesDebit.stream().map(obj -> new DespesaDTO(obj)).collect(Collectors.toList());
			listExpensesDTO.addAll(listExpensesDebitDTO);
			
			List<DespesaCredito> listExpensesCredit = despesaCreditoRepository.findByCategoriaDespesaAndDataBetween(categoriaDespesa, timeStart, timeEnd);
			List<DespesaDTO> listExpensesCreditDTO = listExpensesCredit.stream().map(obj -> new DespesaDTO(obj)).collect(Collectors.toList());
			listExpensesDTO.addAll(listExpensesCreditDTO);
			
			List<DespesaFinanciamentoEmprestimo> listExpensesFinancing = despesaFinanciamentoEmprestimoRepository.findByCategoriaDespesaAndDataBetween(categoriaDespesa, timeStart, timeEnd);
			List<DespesaDTO> listExpensesFinancingDTO = listExpensesFinancing.stream().map(obj -> new DespesaDTO(obj)).collect(Collectors.toList());
			listExpensesDTO.addAll(listExpensesFinancingDTO);
			
			despesasPorCategoria.put(categoriaDespesa.getNome(), listExpensesDTO);

		}
		return despesasPorCategoria;
	}

	public List<DespesaDTO> findAllExpenses(){
		List<DespesaDTO> listExpensesDTO = new ArrayList<>();
		
		List<DespesaDebitoDinheiro> listExpensesDebit = despesaDebitoDinheiroRepository.findAll();
		List<DespesaDTO> listExpensesDebitDTO = listExpensesDebit.stream().map(obj -> new DespesaDTO(obj)).collect(Collectors.toList());
		listExpensesDTO.addAll(listExpensesDebitDTO);
		
		List<DespesaCredito> listExpensesCredit = despesaCreditoRepository.findAll();
		List<DespesaDTO> listExpensesCreditDTO = listExpensesCredit.stream().map(obj -> new DespesaDTO(obj)).collect(Collectors.toList());
		listExpensesDTO.addAll(listExpensesCreditDTO);
		
		List<DespesaFinanciamentoEmprestimo> listExpensesFinancing = despesaFinanciamentoEmprestimoRepository.findAll();
		List<DespesaDTO> listExpensesFinancingDTO = listExpensesFinancing.stream().map(obj -> new DespesaDTO(obj)).collect(Collectors.toList());
		listExpensesDTO.addAll(listExpensesFinancingDTO);
				
		return listExpensesDTO;
	}
	
	public List<?> findRecentTransactions() {
		List<ReceitaDTO> listRecentIncomes = receitaService.findRecentIncomes();
		List<DespesaDTO> listRecentExpensesDebitCash = despesaDebitoDinheiroService.findRecentExpensesDebitCash();
		List<DespesaDTO> listRecentExpensesCredit = despesaCreditoService.findRecentExpensesCredit();
		List<DespesaDTO> listRecentExpensesFinancingLoan = despesaFinanciamentoEmprestimoService.findRecentExpensesFinancingLoan();
		
		List<?> listRecentTransactions = Stream.concat(Stream.concat(Stream.concat(listRecentIncomes.stream(), listRecentExpensesDebitCash.stream()), listRecentExpensesCredit.stream()),listRecentExpensesFinancingLoan.stream()).collect(Collectors.toList());
		
		return listRecentTransactions;
	}
	
	public List<DespesaDTO> findRecentExpenses() {
		
		List<DespesaDTO> listRecentExpensesDebitCash = despesaDebitoDinheiroService.findRecentExpensesDebitCash();
		List<DespesaDTO> listRecentExpensesCredit = despesaCreditoService.findRecentExpensesCredit();
		List<DespesaDTO> listRecentExpensesFinancingLoan = despesaFinanciamentoEmprestimoService.findRecentExpensesFinancingLoan();
		
		List<DespesaDTO> listRecentExpenses = Stream.concat(Stream.concat(listRecentExpensesDebitCash.stream(), listRecentExpensesCredit.stream()),listRecentExpensesFinancingLoan.stream()).collect(Collectors.toList());
		
		Collections.sort(listRecentExpenses, new Comparator<DespesaDTO>() {
			public int compare(DespesaDTO one, DespesaDTO other) {
				return other.getData().compareTo(one.getData()); // fazendo dessa forma os registros são ordenados do mais recente para o mais antigo
			}
		});
		
		List<DespesaDTO> firstFiveExpenses = listRecentExpenses.stream().limit(5).collect(Collectors.toList()); // retorna as 5 despesas mais recentes
		
		return firstFiveExpenses;
	}
	
	public Hashtable<String, Object> calculateSumExpensesByCategoryAndByPeriod(Date timeStart, Date timeEnd) {
		Hashtable<String, Object> totalExpensesByCategory = new Hashtable<String, Object>();

		Hashtable<String, List<DespesaDTO>> despesasPorCategoria = this.findExpensesByCategoryAndByPeriod(timeStart, timeEnd);

		Set<String> setOfKeys = despesasPorCategoria.keySet();

		for (String key : setOfKeys) {
			float sumExpensesByCategory = 0;
			List<DespesaDTO> listaDespesas = despesasPorCategoria.get(key);
			for (DespesaDTO despesa : listaDespesas) {
				sumExpensesByCategory = sumExpensesByCategory + despesa.getValor();
			}
			totalExpensesByCategory.put(key, sumExpensesByCategory);
		}
		return totalExpensesByCategory;
	}
	
	public Hashtable<String, Object> calculateSumExpensesByPeriod(Date timeStart, Date timeEnd){
		float sumExpensesByPeriod = 0;
		Hashtable<String, Object> totalExpensesByPeriod = new Hashtable<String, Object>();
		
		Hashtable<String, Object> totalExpensesByCategory = this.calculateSumExpensesByCategoryAndByPeriod(timeStart, timeEnd);
		
		Set<String> setOfKeys = totalExpensesByCategory.keySet();
		
		for (String key : setOfKeys) {
			float actualValue = (float) totalExpensesByCategory.get(key);
			sumExpensesByPeriod = sumExpensesByPeriod + actualValue;
		}
		
		totalExpensesByPeriod.put("Total", sumExpensesByPeriod);
		
		return totalExpensesByPeriod;
	}

}
