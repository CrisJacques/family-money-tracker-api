package com.cristhiane.familymoneytrackerapi.controllers;

import java.net.URI;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.cristhiane.familymoneytrackerapi.domain.Receita;
import com.cristhiane.familymoneytrackerapi.dto.ReceitaDTO;
import com.cristhiane.familymoneytrackerapi.service.ReceitaService;
import com.cristhiane.familymoneytrackerapi.utils.DefaultPeriodOfSearch;

@RestController
@RequestMapping(value = "/api/receitas")
public class ReceitaController {
	
	@Autowired
	ReceitaService service;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> findIncomes(@RequestParam(defaultValue = "false") String recentes,
			@RequestParam(defaultValue = "false") String por_categoria, @RequestParam(required = false) String start,
			@RequestParam(required = false) String end) {
		List<ReceitaDTO> listaReceitas = new ArrayList<>();

		if (recentes.equals("true") && por_categoria.equals("true")) {
			return ResponseEntity.badRequest().body(
					"Não é possível retornar as receitas recentes e a listagem de receitas por categoria na mesma requisição. Escolha apenas uma das duas opções e tente novamente");
		} else {
			if (recentes.equals("false") && por_categoria.equals("false")) {
				listaReceitas = service.findAll();
				return ResponseEntity.ok().body(listaReceitas);
			} else if (recentes.equals("true") && por_categoria.equals("false")) {
				listaReceitas = service.findRecentIncomes();
				return ResponseEntity.ok().body(listaReceitas);
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
						// responsável pela busca de receitas por categoria dentro do período informado.
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
				// Chamando o service responsável por retornar a lista de receitas por
				// categoria, passando as datas de início e final vindas da requisição, ou os
				// valores configurados segundo a lógica acima caso o período de busca não seja
				// informado na requisição
				Hashtable<String, List<ReceitaDTO>> receitasPorCategoria = service
						.findIncomesByCategoryAndByPeriod(startDate, endDate);
				return ResponseEntity.ok().body(receitasPorCategoria);
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
	public ResponseEntity<?> calculateSumIncomesByCategoryAndByPeriod(@RequestParam(required = false) String start,
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
				// responsável pelo somatório de valores de receitas por categoria dentro do
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

		Hashtable<String, Object> obj = service.calculateSumIncomesByCategoryAndByPeriod(startDate, endDate);

		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(value = "/total-geral", method = RequestMethod.GET)
	public ResponseEntity<?> calculateSumIncomesByPeriod(@RequestParam(required = false) String start,
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
				// responsável pelo somatório de valores de receitas dentro do
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

		Hashtable<String, Object> obj = service.calculateSumIncomesByPeriod(startDate, endDate);

		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id){
		Receita obj = service.find(id);
		
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> insert(@RequestBody Receita obj){
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();// criando URI para o recurso recém criado
		
		return ResponseEntity.created(uri).build();// retorna status code 201 e no cabeçalho da resposta informa a uri do recurso recém criado
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody Receita obj, @PathVariable Integer id){
		obj.setId(id);
		obj = service.update(obj);
		
		return ResponseEntity.noContent().build();// retorna status code 204
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id){
		service.delete(id);
		
		return ResponseEntity.noContent().build();// retorna status code 204
	}

}
