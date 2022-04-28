package com.cristhiane.familymoneytrackerapi.service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.cristhiane.familymoneytrackerapi.domain.DespesaCredito;
import com.cristhiane.familymoneytrackerapi.domain.DespesaDebitoDinheiro;
import com.cristhiane.familymoneytrackerapi.domain.DespesaFinanciamentoEmprestimo;
import com.cristhiane.familymoneytrackerapi.domain.Receita;
import com.cristhiane.familymoneytrackerapi.dto.DespesaDTO;
import com.cristhiane.familymoneytrackerapi.dto.ReceitaDTO;
import com.cristhiane.familymoneytrackerapi.repository.DespesaCreditoRepository;
import com.cristhiane.familymoneytrackerapi.repository.DespesaDebitoDinheiroRepository;
import com.cristhiane.familymoneytrackerapi.repository.DespesaFinanciamentoEmprestimoRepository;
import com.cristhiane.familymoneytrackerapi.repository.ReceitaRepository;

@Service
public class TransacaoService {
	
	@Autowired
	private ReceitaRepository receitaRepository;
	
	@Autowired
	DespesaDebitoDinheiroRepository despesaDebitoDinheiroRepository;
	
	@Autowired
	DespesaCreditoRepository despesaCreditoRepository;
	
	@Autowired
	DespesaFinanciamentoEmprestimoRepository despesaFinanciamentoEmprestimoRepository;
	
	private Pageable fiveMostRecent = PageRequest.of(0, 5, Sort.by("data").descending());
	
	public List<ReceitaDTO> findRecentIncomes() {
		List<Receita> list = receitaRepository.findAll(this.fiveMostRecent).getContent();
		return list.stream().map(obj -> new ReceitaDTO(obj)).collect(Collectors.toList());
	}
	
	public List<DespesaDTO> findRecentExpensesDebitCash() {
		List<DespesaDebitoDinheiro> list = despesaDebitoDinheiroRepository.findAll(this.fiveMostRecent).getContent();
		return list.stream().map(obj -> new DespesaDTO(obj)).collect(Collectors.toList());
	}
	
	public List<DespesaDTO> findRecentExpensesCredit() {
		List<DespesaCredito> list = despesaCreditoRepository.findAll(this.fiveMostRecent).getContent();
		return list.stream().map(obj -> new DespesaDTO(obj)).collect(Collectors.toList());
	}
	
	public List<DespesaDTO> findRecentExpensesFinancingLoan() {
		List<DespesaFinanciamentoEmprestimo> list = despesaFinanciamentoEmprestimoRepository.findAll(this.fiveMostRecent).getContent();
		return list.stream().map(obj -> new DespesaDTO(obj)).collect(Collectors.toList());
	}
	
	public List<?> listRecentTransactions() {
		List<ReceitaDTO> listRecentIncomes = this.findRecentIncomes();
		List<DespesaDTO> listRecentExpensesDebitCash = this.findRecentExpensesDebitCash();
		List<DespesaDTO> listRecentExpensesCredit = this.findRecentExpensesCredit();
		List<DespesaDTO> listRecentExpensesFinancingLoan = this.findRecentExpensesFinancingLoan();
		
		List<?> listRecentTransactions = Stream.concat(Stream.concat(Stream.concat(listRecentIncomes.stream(), listRecentExpensesDebitCash.stream()), listRecentExpensesCredit.stream()),listRecentExpensesFinancingLoan.stream()).collect(Collectors.toList());
		
		return listRecentTransactions;
	}

}
