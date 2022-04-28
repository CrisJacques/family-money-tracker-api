package com.cristhiane.familymoneytrackerapi.service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cristhiane.familymoneytrackerapi.dto.DespesaDTO;
import com.cristhiane.familymoneytrackerapi.dto.ReceitaDTO;

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

	public List<?> listRecentTransactions() {
		List<ReceitaDTO> listRecentIncomes = receitaService.findRecentIncomes();
		List<DespesaDTO> listRecentExpensesDebitCash = despesaDebitoDinheiroService.findRecentExpensesDebitCash();
		List<DespesaDTO> listRecentExpensesCredit = despesaCreditoService.findRecentExpensesCredit();
		List<DespesaDTO> listRecentExpensesFinancingLoan = despesaFinanciamentoEmprestimoService.findRecentExpensesFinancingLoan();
		
		List<?> listRecentTransactions = Stream.concat(Stream.concat(Stream.concat(listRecentIncomes.stream(), listRecentExpensesDebitCash.stream()), listRecentExpensesCredit.stream()),listRecentExpensesFinancingLoan.stream()).collect(Collectors.toList());
		
		return listRecentTransactions;
	}
	
	public List<DespesaDTO> listRecentExpenses() {
		
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

}
