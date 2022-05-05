package com.cristhiane.familymoneytrackerapi.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cristhiane.familymoneytrackerapi.service.TransacaoService;
import com.cristhiane.familymoneytrackerapi.utils.DefaultPeriodOfSearch;

@RestController
@RequestMapping(value = "/api/transacoes")
public class TransacaoController {
	
	@Autowired
	TransacaoService service;
	
	@RequestMapping(value = "/recentes",method = RequestMethod.GET)
	public ResponseEntity<List<?>> findRecentTransactions() {
		List<?> list = service.findRecentTransactions();
		return ResponseEntity.ok().body(list);
	}
	
	@RequestMapping(value = "/total-geral", method = RequestMethod.GET)
	public ResponseEntity<?> calculateSumExpensesByPeriod(@RequestParam(required = false) String start,
			@RequestParam(required = false) String end) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date startDate = null;
		Date endDate = null;

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
				// do início e final do período e tais valores são repassados para o service
				// responsável pelo somatório de valores de receitas e despesas dentro do
				// período informado.
				// Se algo der errado ao fazer o parse dos valores informados na requisição, é
				// retornado um erro 400, para que o cliente saiba que há algo errado com as
				// datas informadas.
			try {
				startDate = sdf.parse(start);
			} catch (ParseException e) {
				e.printStackTrace();
				return ResponseEntity.badRequest().body(
						"Formato inválido de data de início do período (parâmetro start). O formato esperado é dd/MM/yyyy");
			}
			if (end == null) {
				return ResponseEntity.badRequest()
						.body("Se o parâmetro start foi informado, o parâmetro end também deve ser informado.");
			} else {
				try {
					endDate = sdf.parse(end);
				} catch (ParseException e) {
					e.printStackTrace();
					return ResponseEntity.badRequest().body(
							"Formato inválido de data de final do período (parâmetro end). O formato esperado é dd/MM/yyyy");
				}
			}
		}

		Hashtable<String, Object> obj = service.calculateSumIncomesAndExpensesByPeriod(startDate, endDate);

		return ResponseEntity.ok().body(obj);
	}

}
