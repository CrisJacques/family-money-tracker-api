package com.cristhiane.familymoneytrackerapi.controllers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cristhiane.familymoneytrackerapi.dto.DespesaDTO;
import com.cristhiane.familymoneytrackerapi.service.DespesaCreditoService;
import com.cristhiane.familymoneytrackerapi.service.DespesaDebitoDinheiroService;
import com.cristhiane.familymoneytrackerapi.service.DespesaFinanciamentoEmprestimoService;
import com.cristhiane.familymoneytrackerapi.service.TransacaoService;
import com.cristhiane.familymoneytrackerapi.utils.DefaultPeriodOfSearch;

/**
 * Controller responsável pelas requisições relacionadas a despesas (de uma
 * forma geral, sem levar em conta a forma de pagamento)
 *
 */
@RestController
@RequestMapping(value = "/api/despesas")
public class DespesaController {

	@Autowired
	TransacaoService service;
	
	@Autowired
	DespesaDebitoDinheiroService despesaDebitoDinheiroService;
	
	@Autowired
	DespesaCreditoService despesaCreditoService;
	
	@Autowired
	DespesaFinanciamentoEmprestimoService despesaFinanciamentoEmprestimoService;

	/**
	 * Busca por despesas cadastradas de acordo com filtros passados por parâmetro.
	 * Se nenhum filtro for passado, retorna todas as despesas cadastradas
	 * 
	 * @param recentes      - Se valor for true, retorna as 5 despesas mais recentes
	 *                      do mês atual
	 * @param por_categoria - Se valor for true, retorna uma lista de despesas por
	 *                      categoria no período informado pelos parâmetros start e
	 *                      end. Se start e end não forem informados, o período a
	 *                      ser buscado será todo o mês atual
	 * @param por_periodo   - Se valor for true, retorna todas as depesas dentro do
	 *                      período informado pelos parâmetros start e end. Se start
	 *                      e end não forem informados, o período a ser buscado será
	 *                      todo o mês atual
	 * @param start         - Início do período a ser buscado
	 * @param end           - Final do período a ser buscado
	 * @return Lista de despesas de acordo com os filtros configurados ou todas as
	 *         despesas caso nenhum filtro seja configurado
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> findIncomes(@RequestParam(defaultValue = "false") String recentes,
			@RequestParam(defaultValue = "false") String por_categoria, @RequestParam(required = false) String start,
			@RequestParam(required = false) String end, @RequestParam(defaultValue = "false") String por_periodo) {
		List<DespesaDTO> listaDespesas = new ArrayList<>();

		// Listando despesas de um período selecionado pelo usuário
		if (por_periodo.equals("true")) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			LocalDate startDate = null;
			LocalDate endDate = null;

			if (start == null) {
				if (end != null) {
					return ResponseEntity.badRequest().body(
							"Se o parâmetro start não foi informado, o parâmetro end também não deve ser informado.");
				} else {
					// Se o período não foi informado na requisição, é feita uma lógica
					// para configurar o período de busca como sendo todo o mês atual (do dia 1 até
					// o último dia do mês)
					startDate = DefaultPeriodOfSearch.setStartOfPeriod();
					endDate = DefaultPeriodOfSearch.setEndOfPeriod();
				}

			} else {// Se o início do período foi informado na requisição, é feito o processamento
				startDate = LocalDate.parse(start, formatter);
				if (end == null) {
					return ResponseEntity.badRequest()
							.body("Se o parâmetro start foi informado, o parâmetro end também deve ser informado.");
				} else {
					endDate = LocalDate.parse(end, formatter);
				}
			}
			listaDespesas = service.findExpensesByPeriod(startDate, endDate);
			return ResponseEntity.ok().body(listaDespesas);
		}

		if (recentes.equals("true") && por_categoria.equals("true")) {
			return ResponseEntity.badRequest().body(
					"Não é possível retornar as despesas recentes e a listagem de despesas por categoria na mesma requisição. Escolha apenas uma das duas opções e tente novamente");
		} else {
			if (recentes.equals("false") && por_categoria.equals("false")) {
				listaDespesas = service.findAllExpenses();
				return ResponseEntity.ok().body(listaDespesas);
			} else if (recentes.equals("true") && por_categoria.equals("false")) {
				listaDespesas = service.findRecentExpenses();
				return ResponseEntity.ok().body(listaDespesas);
			} else if (por_categoria.equals("true") && recentes.equals("false")) {
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
				LocalDate startDate = null;
				LocalDate endDate = null;

				if (start == null) {
					if (end != null) {
						return ResponseEntity.badRequest().body(
								"Se o parâmetro start não foi informado, o parâmetro end também não deve ser informado.");
					} else {
						// Se o período não foi informado na requisição, é feita uma lógica
						// para configurar o período de busca como sendo todo o mês atual (do dia 1 até
						// o último dia do mês)
						startDate = DefaultPeriodOfSearch.setStartOfPeriod();
						endDate = DefaultPeriodOfSearch.setEndOfPeriod();
					}

				} else {// Se o início do período foi informado na requisição, é feito o processamento
					startDate = LocalDate.parse(start, formatter);
					if (end == null) {
						return ResponseEntity.badRequest()
								.body("Se o parâmetro start foi informado, o parâmetro end também deve ser informado.");
					} else {
						endDate = LocalDate.parse(end, formatter);
					}
				}
				// Chamando o service responsável por retornar a lista de despesas por
				// categoria, passando as datas de início e final vindas da requisição, ou os
				// valores configurados segundo a lógica acima caso o período de busca não seja
				// informado na requisição
				Hashtable<String, List<DespesaDTO>> despesasPorCategoria = service
						.findExpensesByCategoryAndByPeriod(startDate, endDate);
				return ResponseEntity.ok().body(despesasPorCategoria);
			} else {// Se o fluxo cair aqui significa que os parâmetros "recentes" e/ou
					// "por_categoria" receberam valores diferentes de true ou false na requisição,
					// tornando a requisição inválida. Dessa forma, é retornado erro 400 (bad
					// request)
				return ResponseEntity.badRequest().body(
						"Os parâmetros da requisição de nome recentes e por_categoria apenas aceitam true ou false como valores válidos");
			}
		}
	}

	/**
	 * Lista o valor total de despesas por categoria dentro de um período informado
	 * pelos parâmetros start e end. Se start e end não forem informados, o período
	 * a ser buscado será todo o mês atual
	 * 
	 * @param start - Início do período dentro do qual as despesas serão somadas
	 * @param end   - Final do período dentro do qual as despesas serão somadas
	 * @return Somatório de despesas por categoria
	 */
	@RequestMapping(value = "/total-por-categoria", method = RequestMethod.GET)
	public ResponseEntity<?> calculateSumExpensesByCategoryAndByPeriod(@RequestParam(required = false) String start,
			@RequestParam(required = false) String end) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate startDate = null;
		LocalDate endDate = null;

		if (start == null) {
			if (end != null) {
				return ResponseEntity.badRequest()
						.body("Se o parâmetro start não foi informado, o parâmetro end também não deve ser informado.");
			} else {
				// Se o período não foi informado na requisição, é feita uma lógica
				// para configurar o período de busca como sendo todo o mês atual (do dia 1 até
				// o último dia do mês)
				startDate = DefaultPeriodOfSearch.setStartOfPeriod();
				endDate = DefaultPeriodOfSearch.setEndOfPeriod();
			}

		} else {// Se o início do período foi informado na requisição, é feito o processamento
			startDate = LocalDate.parse(start, formatter);
			if (end == null) {
				return ResponseEntity.badRequest()
						.body("Se o parâmetro start foi informado, o parâmetro end também deve ser informado.");
			} else {
				endDate = LocalDate.parse(end, formatter);
			}
		}

		Hashtable<String, Object> obj = service.calculateSumExpensesByCategoryAndByPeriod(startDate, endDate);

		return ResponseEntity.ok().body(obj);
	}

	/**
	 * Retorna o somatório total de despesas (incluindo todas as categorias) dentro
	 * de um período especificado pelos parâmetros start e end. Se start e end não
	 * forem informados, o período a ser buscado será todo o mês atual
	 * 
	 * @param start - Início do período dentro do qual as despesas serão somadas
	 * @param end   - Final do período dentro do qual as despesas serão somadas
	 * @return Somatório total de despesas
	 */
	@RequestMapping(value = "/total-geral", method = RequestMethod.GET)
	public ResponseEntity<?> calculateSumExpensesByPeriod(@RequestParam(required = false) String start,
			@RequestParam(required = false) String end) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate startDate = null;
		LocalDate endDate = null;

		if (start == null) {
			if (end != null) {
				return ResponseEntity.badRequest()
						.body("Se o parâmetro start não foi informado, o parâmetro end também não deve ser informado.");
			} else {
				// Se o período não foi informado na requisição, é feita uma lógica
				// para configurar o período de busca como sendo todo o mês atual (do dia 1 até
				// o último dia do mês)
				startDate = DefaultPeriodOfSearch.setStartOfPeriod();
				endDate = DefaultPeriodOfSearch.setEndOfPeriod();
			}

		} else {// Se o início do período foi informado na requisição, é feito o processamento
			startDate = LocalDate.parse(start, formatter);
			if (end == null) {
				return ResponseEntity.badRequest()
						.body("Se o parâmetro start foi informado, o parâmetro end também deve ser informado.");
			} else {
				endDate = LocalDate.parse(end, formatter);
			}
		}

		Hashtable<String, Object> obj = service.calculateSumExpensesByPeriod(startDate, endDate);

		return ResponseEntity.ok().body(obj);
	}
	
	/**
	 * Remove uma despesa
	 * 
	 * @param id - Id da despesa que deve ser removida
	 * @param forma_pagamento - Forma de pagamento da despesa que deve ser removida
	 * @return Status code 204 se remoção ocorreu com sucesso (sem corpo na
	 *         resposta)
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> delete(@PathVariable Integer id, @RequestParam(required = true) String forma_pagamento) {
		try {
			switch (forma_pagamento) {
			case "DINHEIRO":
				despesaDebitoDinheiroService.delete(id);
				break;
			case "DEBITO":
				despesaDebitoDinheiroService.delete(id);
				break;
			case "CARTAO_DE_CREDITO":
				despesaCreditoService.delete(id);
				break;
			case "FINANCIAMENTO":
				despesaFinanciamentoEmprestimoService.delete(id);
				break;
			case "EMPRESTIMO":
				despesaFinanciamentoEmprestimoService.delete(id);
				break;
			default:
				return ResponseEntity.badRequest()
						.body("Forma de pagamento inválida.");
			}
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("Despesa cujo id foi informado não pertence à forma de pagamento informada ou despesa não existe");
		}
		
		return ResponseEntity.noContent().build();// retorna status code 204
		
	}

}
