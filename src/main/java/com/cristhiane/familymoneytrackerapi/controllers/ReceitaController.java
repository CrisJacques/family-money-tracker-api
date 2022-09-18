package com.cristhiane.familymoneytrackerapi.controllers;

import java.net.URI;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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

/**
 * Controller responsável pelas requisições relacionadas a receitas
 *
 */
@RestController
@RequestMapping(value = "/api/receitas")
public class ReceitaController {
	
	@Autowired
	ReceitaService service;
	
	/**
	 * Busca por receitas cadastradas de acordo com filtros passados por parâmetro.
	 * Se nenhum filtro for passado, retorna todas as receitas cadastradas
	 * 
	 * @param recentes      - Se valor for true, retorna as 5 receitas mais recentes
	 *                      do mês atual
	 * @param por_categoria - Se valor for true, retorna uma lista de receitas por
	 *                      categoria no período informado pelos parâmetros start e
	 *                      end. Se start e end não forem informados, o período a
	 *                      ser buscado será todo o mês atual
	 * @param por_periodo   - Se valor for true, retorna todas as receitas dentro do
	 *                      período informado pelos parâmetros start e end. Se start
	 *                      e end não forem informados, o período a ser buscado será
	 *                      todo o mês atual
	 * @param start         - Início do período a ser buscado (só é levado em conta
	 *                      se o parâmetro por_categoria for true)
	 * @param end           - Final do período a ser buscado (só é levado em conta
	 *                      se o parâmetro por_categoria for true)
	 * @return Lista de receitas de acordo com os filtros configurados ou todas as
	 *         receitas caso nenhum filtro seja configurado
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> findIncomes(@RequestParam(defaultValue = "false") String recentes,
			@RequestParam(defaultValue = "false") String por_categoria, @RequestParam(required = false) String start,
			@RequestParam(required = false) String end, @RequestParam(defaultValue = "false") String por_periodo) {
		List<ReceitaDTO> listaReceitas = new ArrayList<>();
		
		// Listando receitas de um período selecionado pelo usuário
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
				listaReceitas = service.findIncomesByPeriod(startDate, endDate);
				return ResponseEntity.ok().body(listaReceitas);
			}
		

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
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
				LocalDate startDate = null;
				LocalDate endDate = null;

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
						startDate = LocalDate.parse(start, formatter);
					if (end == null) {
						return ResponseEntity.badRequest().body(
								"Se o parâmetro start foi informado, o parâmetro end também deve ser informado.");
					}else {
						endDate = LocalDate.parse(end, formatter);
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
	
	/**
	 * Lista o valor total de receitas por categoria dentro de um período informado
	 * pelos parâmetros start e end. Se start e end não forem informados, o período
	 * a ser buscado será todo o mês atual
	 * 
	 * @param start - Início do período dentro do qual as receitas serão somadas
	 * @param end   - Final do período dentro do qual as receitas serão somadas
	 * @return Somatório de receitas por categoria
	 */
	@RequestMapping(value = "/total-por-categoria", method = RequestMethod.GET)
	public ResponseEntity<?> calculateSumIncomesByCategoryAndByPeriod(@RequestParam(required = false) String start,
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

		Hashtable<String, Object> obj = service.calculateSumIncomesByCategoryAndByPeriod(startDate, endDate);

		return ResponseEntity.ok().body(obj);
	}
	
	/**
	 * Retorna o somatório total de receitas (incluindo todas as categorias) dentro
	 * de um período especificado pelos parâmetros start e end. Se start e end não
	 * forem informados, o período a ser buscado será todo o mês atual
	 * 
	 * @param start - Início do período dentro do qual as receitas serão somadas
	 * @param end   - Final do período dentro do qual as receitas serão somadas
	 * @return Somatório total de receitas
	 */
	@RequestMapping(value = "/total-geral", method = RequestMethod.GET)
	public ResponseEntity<?> calculateSumIncomesByPeriod(@RequestParam(required = false) String start,
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

		Hashtable<String, Object> obj = service.calculateSumIncomesByPeriod(startDate, endDate);

		return ResponseEntity.ok().body(obj);
	}
	
	/**
	 * Busca por uma receita cujo id é passado por parâmetro
	 * 
	 * @param id - Id da receita
	 * @return Informações da receita solicitada
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id){
		Receita obj = service.find(id);
		
		return ResponseEntity.ok().body(obj);
	}
	
	/**
	 * Insere uma receita
	 * 
	 * @param obj - Corpo da requisição
	 * @return Caso inserção ocorra com sucesso, retorna a uri do recurso recém
	 *         criado no cabeçalho da resposta
	 */
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> insert(@RequestBody Receita obj){
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();// criando URI para o recurso recém criado
		
		return ResponseEntity.created(uri).build();// retorna status code 201 e no cabeçalho da resposta informa a uri do recurso recém criado
	}
	
	/**
	 * Atualiza as informações de uma receita
	 * 
	 * @param obj - Corpo da requisição
	 * @param id  - Id da receita que deve ser atualizada
	 * @return Status code 204 se atualização ocorreu com sucesso (sem corpo na
	 *         resposta)
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody Receita obj, @PathVariable Integer id){
		obj.setId(id);
		obj = service.update(obj);
		
		return ResponseEntity.noContent().build();// retorna status code 204
	}
	
	/**
	 * Remove uma receita
	 * 
	 * @param id - Id da receita que deve ser removida
	 * @return Status code 204 se remoção ocorreu com sucesso (sem corpo na
	 *         resposta)
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id){
		service.delete(id);
		
		return ResponseEntity.noContent().build();// retorna status code 204
	}

}
