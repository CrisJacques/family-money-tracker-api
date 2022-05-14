package com.cristhiane.familymoneytrackerapi.controllers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Hashtable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cristhiane.familymoneytrackerapi.service.TransacaoService;
import com.cristhiane.familymoneytrackerapi.utils.DefaultPeriodOfSearch;

/**
 * Controller responsável pelas requisições relacionadas a transações (despesas
 * e receitas de uma forma geral)
 *
 */
@RestController
@RequestMapping(value = "/api/transacoes")
public class TransacaoController {

	@Autowired
	TransacaoService service;

	/**
	 * Lista o valor total de despesas e receitas para um período dado pelos
	 * parâmetros start e end. Se start e end não forem informados, o período
	 * buscado será todo o mês atual
	 * 
	 * @param saldo - Se valor for true, retorna também o saldo resultante (receitas
	 *              - despesas)
	 * @param start - Início do período a ser buscado
	 * @param end   - Final do período a ser buscado
	 * @return Valor total de despesas e receitas para um dado período, com o saldo
	 *         junto se for solicitado
	 */
	@RequestMapping(value = "/total-geral", method = RequestMethod.GET)
	public ResponseEntity<?> calculateSumExpensesByPeriod(@RequestParam(required = false) String saldo,
			@RequestParam(required = false) String start, @RequestParam(required = false) String end) {
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

		if (saldo == null) {
			Hashtable<String, Object> obj = service.calculateSumIncomesAndExpensesByPeriod(startDate, endDate, false);
			return ResponseEntity.ok().body(obj);
		} else if (saldo.equals("true")) {
			Hashtable<String, Object> obj = service.calculateSumIncomesAndExpensesByPeriod(startDate, endDate, true);
			return ResponseEntity.ok().body(obj);
		} else if (saldo.equals("false")) {
			Hashtable<String, Object> obj = service.calculateSumIncomesAndExpensesByPeriod(startDate, endDate, false);
			return ResponseEntity.ok().body(obj);
		} else {
			return ResponseEntity.badRequest()
					.body("O parâmetro saldo apenas aceita true ou false como valores válidos");
		}

	}

}
