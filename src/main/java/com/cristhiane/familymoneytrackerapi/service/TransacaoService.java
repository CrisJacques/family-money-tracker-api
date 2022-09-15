package com.cristhiane.familymoneytrackerapi.service;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.math3.util.Precision;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cristhiane.familymoneytrackerapi.domain.CategoriaDespesa;
import com.cristhiane.familymoneytrackerapi.domain.DespesaCredito;
import com.cristhiane.familymoneytrackerapi.domain.DespesaDebitoDinheiro;
import com.cristhiane.familymoneytrackerapi.domain.DespesaFinanciamentoEmprestimo;
import com.cristhiane.familymoneytrackerapi.dto.DespesaDTO;
import com.cristhiane.familymoneytrackerapi.repository.DespesaCreditoRepository;
import com.cristhiane.familymoneytrackerapi.repository.DespesaDebitoDinheiroRepository;
import com.cristhiane.familymoneytrackerapi.repository.DespesaFinanciamentoEmprestimoRepository;

/**
 * Classe que contém os services relacionados às entities Despesa e Receita
 * (quando operações envolvem mais de um tipo de Despesa e também Receita)
 *
 */
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

	/**
	 * Busca por todas as despesas cuja data pertence ao período solicitado e as
	 * organiza por categoria
	 * 
	 * @param timeStart - Data inicial do período a ser buscado
	 * @param timeEnd   - Data final do período a ser buscado
	 * @return Lista de todas as despesas pertencentes ao período solicitado
	 *         (contemplando todas as formas de pagamento), contendo apenas as
	 *         informações essenciais (DTOs)
	 */
	public Hashtable<String, List<DespesaDTO>> findExpensesByCategoryAndByPeriod(LocalDate timeStart,
			LocalDate timeEnd) {
		Hashtable<String, List<DespesaDTO>> despesasPorCategoria = new Hashtable<String, List<DespesaDTO>>();

		List<CategoriaDespesa> listaCategoriaDespesa = categoriaDespesaService.findAll();

		for (CategoriaDespesa categoriaDespesa : listaCategoriaDespesa) {
			List<DespesaDTO> listExpensesDTO = new ArrayList<>();

			List<DespesaDebitoDinheiro> listExpensesDebit = despesaDebitoDinheiroRepository
					.findByCategoriaDespesaAndDataBetween(categoriaDespesa, timeStart, timeEnd);
			List<DespesaDTO> listExpensesDebitDTO = listExpensesDebit.stream().map(obj -> new DespesaDTO(obj))
					.collect(Collectors.toList());
			listExpensesDTO.addAll(listExpensesDebitDTO);

			List<DespesaCredito> listExpensesCredit = despesaCreditoRepository
					.findByCategoriaDespesaAndDataBetween(categoriaDespesa, timeStart, timeEnd);
			List<DespesaDTO> listExpensesCreditDTO = listExpensesCredit.stream().map(obj -> new DespesaDTO(obj))
					.collect(Collectors.toList());
			listExpensesDTO.addAll(listExpensesCreditDTO);

			List<DespesaFinanciamentoEmprestimo> listExpensesFinancing = despesaFinanciamentoEmprestimoRepository
					.findByCategoriaDespesaAndDataBetween(categoriaDespesa, timeStart, timeEnd);
			List<DespesaDTO> listExpensesFinancingDTO = listExpensesFinancing.stream().map(obj -> new DespesaDTO(obj))
					.collect(Collectors.toList());
			listExpensesDTO.addAll(listExpensesFinancingDTO);

			despesasPorCategoria.put(categoriaDespesa.getNome(), listExpensesDTO);

		}
		return despesasPorCategoria;
	}

	/**
	 * Busca por todas as despesas cuja data pertence ao período solicitado
	 * 
	 * @param timeStart - Data inicial do período a ser buscado
	 * @param timeEnd   - Data final do período a ser buscado
	 * @return Lista de todas as despesas pertencentes ao período solicitado
	 *         contendo apenas as informações essenciais (DTOs), ordenadas do
	 *         registro mais recente para o mais antigo
	 */
	public List<DespesaDTO> findExpensesByPeriod(LocalDate timeStart, LocalDate timeEnd) {

		List<DespesaDebitoDinheiro> listExpensesDebit = despesaDebitoDinheiroRepository.findAllByDataBetween(timeStart,
				timeEnd);
		List<DespesaDTO> listExpensesDebitDTO = listExpensesDebit.stream().map(obj -> new DespesaDTO(obj))
				.collect(Collectors.toList());

		List<DespesaCredito> listExpensesCredit = despesaCreditoRepository.findAllByDataBetween(timeStart, timeEnd);
		List<DespesaDTO> listExpensesCreditDTO = listExpensesCredit.stream().map(obj -> new DespesaDTO(obj))
				.collect(Collectors.toList());

		List<DespesaFinanciamentoEmprestimo> listExpensesFinancing = despesaFinanciamentoEmprestimoRepository
				.findAllByDataBetween(timeStart, timeEnd);
		List<DespesaDTO> listExpensesFinancingDTO = listExpensesFinancing.stream().map(obj -> new DespesaDTO(obj))
				.collect(Collectors.toList());

		List<DespesaDTO> despesasDoPeriodoOrdenadas = Stream
				.concat(Stream.concat(listExpensesDebitDTO.stream(), listExpensesCreditDTO.stream()),
						listExpensesFinancingDTO.stream())
				.collect(Collectors.toList());

		Collections.sort(despesasDoPeriodoOrdenadas, new Comparator<DespesaDTO>() {
			public int compare(DespesaDTO one, DespesaDTO other) {
				return other.getData().compareTo(one.getData()); // fazendo dessa forma os registros são ordenados do
																	// mais recente para o mais antigo
			}
		});

		return despesasDoPeriodoOrdenadas;
	}

	/**
	 * Busca por todas as despesas cadastradas, contemplando todas as formas de
	 * pagamento
	 * 
	 * @return Lista de todas as despesas cadastradas, contendo apenas as
	 *         informações essenciais (DTOs)
	 */
	public List<DespesaDTO> findAllExpenses() {
		List<DespesaDTO> listExpensesDTO = new ArrayList<>();

		List<DespesaDebitoDinheiro> listExpensesDebit = despesaDebitoDinheiroRepository.findAll();
		List<DespesaDTO> listExpensesDebitDTO = listExpensesDebit.stream().map(obj -> new DespesaDTO(obj))
				.collect(Collectors.toList());
		listExpensesDTO.addAll(listExpensesDebitDTO);

		List<DespesaCredito> listExpensesCredit = despesaCreditoRepository.findAll();
		List<DespesaDTO> listExpensesCreditDTO = listExpensesCredit.stream().map(obj -> new DespesaDTO(obj))
				.collect(Collectors.toList());
		listExpensesDTO.addAll(listExpensesCreditDTO);

		List<DespesaFinanciamentoEmprestimo> listExpensesFinancing = despesaFinanciamentoEmprestimoRepository.findAll();
		List<DespesaDTO> listExpensesFinancingDTO = listExpensesFinancing.stream().map(obj -> new DespesaDTO(obj))
				.collect(Collectors.toList());
		listExpensesDTO.addAll(listExpensesFinancingDTO);

		return listExpensesDTO;
	}

	/**
	 * Busca pelas despesas mais recentes dentro do mês atual, contemplando todas as
	 * formas de pagamento
	 * 
	 * @return Lista das 5 despesas mais recentes dentro do mês atual, ordenadas da
	 *         mais recente para a mais antiga, contendo apenas as informações
	 *         essenciais (DTOs)
	 */
	public List<DespesaDTO> findRecentExpenses() {

		List<DespesaDTO> listRecentExpensesDebitCash = despesaDebitoDinheiroService.findRecentExpensesDebitCash();
		List<DespesaDTO> listRecentExpensesCredit = despesaCreditoService.findRecentExpensesCredit();
		List<DespesaDTO> listRecentExpensesFinancingLoan = despesaFinanciamentoEmprestimoService
				.findRecentExpensesFinancingLoan();

		List<DespesaDTO> listRecentExpenses = Stream
				.concat(Stream.concat(listRecentExpensesDebitCash.stream(), listRecentExpensesCredit.stream()),
						listRecentExpensesFinancingLoan.stream())
				.collect(Collectors.toList());

		Collections.sort(listRecentExpenses, new Comparator<DespesaDTO>() {
			public int compare(DespesaDTO one, DespesaDTO other) {
				return other.getData().compareTo(one.getData()); // fazendo dessa forma os registros são ordenados do
																	// mais recente para o mais antigo
			}
		});

		List<DespesaDTO> firstFiveExpenses = listRecentExpenses.stream().limit(5).collect(Collectors.toList()); // retorna
																												// as 5
																												// despesas
																												// mais
																												// recentes

		return firstFiveExpenses;
	}

	/**
	 * Calcula o somatório de todas as despesas (contemplando todas as formas de
	 * pagamento) por categoria dentro do período solicitado
	 * 
	 * @param timeStart - Data inicial do período a ser buscado
	 * @param timeEnd   - Data final do período a ser buscado
	 * @return Lista de somatórios das despesas por categoria dentro do período
	 *         solicitado
	 */
	public Hashtable<String, Object> calculateSumExpensesByCategoryAndByPeriod(LocalDate timeStart, LocalDate timeEnd) {
		Hashtable<String, Object> totalExpensesByCategory = new Hashtable<String, Object>();

		Hashtable<String, List<DespesaDTO>> despesasPorCategoria = this.findExpensesByCategoryAndByPeriod(timeStart,
				timeEnd);

		Set<String> setOfKeys = despesasPorCategoria.keySet();

		for (String key : setOfKeys) {
			float sumExpensesByCategory = 0;
			List<DespesaDTO> listaDespesas = despesasPorCategoria.get(key);
			for (DespesaDTO despesa : listaDespesas) {
				sumExpensesByCategory = sumExpensesByCategory + despesa.getValor();
			}
			totalExpensesByCategory.put(key, Precision.round(sumExpensesByCategory, 2));
		}
		return totalExpensesByCategory;
	}

	/**
	 * Calcula o somatório total de despesas (contemplando todas as formas de
	 * pagamento) dentro de um período solicitado, levando em conta todas as
	 * categorias
	 * 
	 * @param timeStart - Data inicial do período a ser buscado
	 * @param timeEnd   - Data final do período a ser buscado
	 * @return Somatório total de despesas dentro do período solicitado
	 */
	public Hashtable<String, Object> calculateSumExpensesByPeriod(LocalDate timeStart, LocalDate timeEnd) {
		float sumExpensesByPeriod = 0;
		Hashtable<String, Object> totalExpensesByPeriod = new Hashtable<String, Object>();

		Hashtable<String, Object> totalExpensesByCategory = this.calculateSumExpensesByCategoryAndByPeriod(timeStart,
				timeEnd);

		Set<String> setOfKeys = totalExpensesByCategory.keySet();

		for (String key : setOfKeys) {
			float actualValue = (float) totalExpensesByCategory.get(key);
			sumExpensesByPeriod = sumExpensesByPeriod + actualValue;
		}

		totalExpensesByPeriod.put("Total", Precision.round(sumExpensesByPeriod, 2));

		return totalExpensesByPeriod;
	}

	/**
	 * Calcula o somatório total de receitas e despesas dentro de um período
	 * solicitado, além do saldo resultante
	 * 
	 * @param timeStart   - Data inicial do período a ser buscado
	 * @param timeEnd     - Data final do período a ser buscado
	 * @param withBalance - Se saldo resultante deve ser calculado também
	 * @return Somatório total de receitas e despesas dentro do período, junto com o
	 *         saldo se for solicitado
	 */
	public Hashtable<String, Object> calculateSumIncomesAndExpensesByPeriod(LocalDate timeStart, LocalDate timeEnd,
			boolean withBalance) {
		DecimalFormat df = new DecimalFormat("0.00");
		DecimalFormatSymbols dfs = df.getDecimalFormatSymbols();

		dfs.setDecimalSeparator('.');
		df.setDecimalFormatSymbols(dfs);

		float totalIncomes = 0;
		float totalExpenses = 0;

		Hashtable<String, Object> totalIncomesAndExpensesByPeriod = new Hashtable<String, Object>();

		Hashtable<String, Object> totalExpensesByPeriod = this.calculateSumExpensesByPeriod(timeStart, timeEnd);
		Hashtable<String, Object> totalIncomesByPeriod = receitaService.calculateSumIncomesByPeriod(timeStart, timeEnd);

		Set<String> setOfKeysIncomes = totalIncomesByPeriod.keySet();
		for (String key : setOfKeysIncomes) {
			totalIncomes = (float) totalIncomesByPeriod.get(key);
			totalIncomesAndExpensesByPeriod.put("Receitas", totalIncomesByPeriod.get(key));
		}

		Set<String> setOfKeysExpenses = totalExpensesByPeriod.keySet();
		for (String key : setOfKeysExpenses) {
			totalExpenses = (float) totalExpensesByPeriod.get(key);
			totalIncomesAndExpensesByPeriod.put("Despesas", totalExpensesByPeriod.get(key));
		}

		if (withBalance) {
			float balance = totalIncomes - totalExpenses;

			totalIncomesAndExpensesByPeriod.put("Saldo", df.format(balance));
		}
		return totalIncomesAndExpensesByPeriod;

	}

}
