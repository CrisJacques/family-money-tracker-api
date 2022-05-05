package com.cristhiane.familymoneytrackerapi.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cristhiane.familymoneytrackerapi.dto.DespesaDTO;
import com.cristhiane.familymoneytrackerapi.service.TransacaoService;
import com.cristhiane.familymoneytrackerapi.utils.DefaultPeriodOfSearch;

@RestController
@RequestMapping(value = "/api/despesas")
public class DespesaController {
	
	@Autowired
	TransacaoService service;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> findIncomes(@RequestParam(defaultValue = "false") String recentes,
			@RequestParam(defaultValue = "false") String por_categoria, @RequestParam(required = false) String start,
			@RequestParam(required = false) String end) {
		List<DespesaDTO> listaDespesas = new ArrayList<>();

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
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				Date startDate = null;
				Date endDate = null;

				if (start == null) {
					if (end != null) {
						return ResponseEntity.badRequest().body(
								"Se o parâmetro start não foi informado, o parâmetro end também não deve ser informado.");
					}else {
						// Se o período não foi informado na requisição, é feita uma lógica
						// para configurar o período de busca como sendo todo o mês atual (do dia 1 até
						// o último dia do mês)
						startDate = DefaultPeriodOfSearch.setStartOfPeriod();
						endDate = DefaultPeriodOfSearch.setEndOfPeriod();
					}			
					
				} else {// Se o início do período foi informado na requisição, é feito o processamento
						// do início e final do período e tais valores são repassados para o service
						// responsável pela busca de despesas por categoria dentro do período informado.
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
						return ResponseEntity.badRequest().body(
								"Se o parâmetro start foi informado, o parâmetro end também deve ser informado.");
					}else {
						try {
							endDate = sdf.parse(end);
						} catch (ParseException e) {
							e.printStackTrace();
							return ResponseEntity.badRequest().body(
									"Formato inválido de data de final do período (parâmetro end). O formato esperado é dd/MM/yyyy");
						}
					}	
				}
				// Chamando o service responsável por retornar a lista de despesas por
				// categoria, passando as datas de início e final vindas da requisição, ou os
				// valores configurados segundo a lógica acima caso o período de busca não seja
				// informado na requisição
				Hashtable<String, List<DespesaDTO>> despesasPorCategoria = service.findExpensesByCategoryAndByPeriod(startDate, endDate);
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
	
	
	@RequestMapping(value = "/total-por-categoria", method = RequestMethod.GET)
	public ResponseEntity<?> calculateSumExpensesByCategoryAndByPeriod(@RequestParam(required = false) String start,
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
				// responsável pelo somatório de valores de despesas por categoria dentro do
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

		Hashtable<String, Object> obj = service.calculateSumExpensesByCategoryAndByPeriod(startDate, endDate);

		return ResponseEntity.ok().body(obj);
	}
}
