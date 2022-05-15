package com.cristhiane.familymoneytrackerapi.service;

import java.time.LocalDate;
import java.util.Hashtable;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.cristhiane.familymoneytrackerapi.domain.CategoriaDespesa;
import com.cristhiane.familymoneytrackerapi.domain.DespesaCredito;
import com.cristhiane.familymoneytrackerapi.dto.DespesaDTO;
import com.cristhiane.familymoneytrackerapi.repository.DespesaCreditoRepository;
import com.cristhiane.familymoneytrackerapi.service.exceptions.DataIntegrityException;
import com.cristhiane.familymoneytrackerapi.service.exceptions.ObjetoNaoEncontradoException;
import com.cristhiane.familymoneytrackerapi.utils.DefaultPeriodOfSearch;

/**
 * Classe que contém os services relacionados à entity Despesa cuja forma de
 * pagamento é cartão de crédito
 *
 */
@Service
public class DespesaCreditoService {
	@Autowired
	private DespesaCreditoRepository repo;

	@Autowired
	private CategoriaDespesaService categoriaDespesaService;

	/**
	 * Busca por todas as despesas (cuja forma de pagamento é cartão de crédito)
	 * dentro de um período informado, organizando-as por categoria
	 * 
	 * @param timeStart - Data inicial do período a ser buscado
	 * @param timeEnd   - Data final do período a ser buscado
	 * @return Lista de todas as despesas dentro do período, organizadas por
	 *         categoria, contendo apenas as informações essenciais (DTOs)
	 */
	public Hashtable<String, List<DespesaDTO>> findCreditExpensesByCategoryAndByPeriod(LocalDate timeStart,
			LocalDate timeEnd) {
		Hashtable<String, List<DespesaDTO>> despesasPorCategoria = new Hashtable<String, List<DespesaDTO>>();

		List<CategoriaDespesa> listaCategoriaDespesa = categoriaDespesaService.findAll();

		for (CategoriaDespesa categoriaDespesa : listaCategoriaDespesa) {
			List<DespesaCredito> listCreditExpenses = repo.findByCategoriaDespesaAndDataBetween(categoriaDespesa,
					timeStart, timeEnd);
			List<DespesaDTO> listCreditExpensesDTO = listCreditExpenses.stream().map(obj -> new DespesaDTO(obj))
					.collect(Collectors.toList());

			despesasPorCategoria.put(categoriaDespesa.getNome(), listCreditExpensesDTO);

		}
		return despesasPorCategoria;
	}

	/**
	 * Busca por todas as despesas (cuja forma de pagamento é cartão de crédito)
	 * cuja data pertence ao mês atual
	 * 
	 * @return Lista de todas as despesas cuja data pertence ao mês atual, contendo
	 *         apenas as informações essenciais (DTOs)
	 */
	public List<DespesaDTO> findRecentExpensesCredit() {
		// Configurando o período de busca como sendo todo o mês atual
		LocalDate startLocalDate = DefaultPeriodOfSearch.setStartOfPeriod();
		LocalDate endLocalDate = DefaultPeriodOfSearch.setEndOfPeriod();

		List<DespesaCredito> list = repo.findAllByDataBetween(startLocalDate, endLocalDate);

		return list.stream().map(obj -> new DespesaDTO(obj)).collect(Collectors.toList());
	}

	/**
	 * Busca pela despesa (cuja forma de pagamento é cartão de crédito) cujo id é
	 * passado por parâmetro
	 * 
	 * @param id - Id da despesa
	 * @return Informações da despesa solicitada
	 * @throws ObjetoNaoEncontradoException Caso não encontre nenhuma despesa com o
	 *                                      id informado
	 */
	public DespesaCredito find(Integer id) {
		Optional<DespesaCredito> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjetoNaoEncontradoException(
				"Objeto não encontrado. ID: " + id + ", Tipo: " + DespesaCredito.class.getName()));
	}

	/**
	 * Cadastra uma despesa (cuja forma de pagamento é cartão de crédito)
	 * 
	 * @param obj - Objeto com as informações da despesa a ser cadastrada
	 * @return Informações da despesa recém cadastrada
	 */
	public DespesaCredito insert(DespesaCredito obj) {
		obj.setId(null); // garantindo que o id do objeto seja nulo para que ele seja inserido no banco
							// de dados
		return repo.save(obj);
	}

	/**
	 * Atualiza as informações de uma despesa (cuja forma de pagamento é cartão de
	 * crédito)
	 * 
	 * @param obj - Objeto com as novas informações da despesa
	 * @return Informações da despesa recém atualizada
	 */
	public DespesaCredito update(DespesaCredito obj) {
		find(obj.getId()); // verificando se o registro existe antes de atualizá-lo
		return repo.save(obj);
	}

	/**
	 * Remove uma despesa (cuja forma de pagamento é cartão de crédito)
	 * 
	 * @param id - Id da despesa a ser removida
	 * @throws DataIntegrityException Caso a despesa não possa ser deletada, pois
	 *                                isso afetaria a integridade dos dados
	 */
	public void delete(Integer id) {
		find(id); // verificando se o objeto existe antes de deletar
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {// Exceção lançada se a exclusão do objeto afetar a integridade de
														// dados do banco
			throw new DataIntegrityException("Despesa não pode ser deletada!");
		}
	}

	/**
	 * Busca por todas as despesas (cuja forma de pagamento é cartão de crédito)
	 * cadastradas
	 * 
	 * @return Lista de todas as despesas cadastradas
	 */
	public List<DespesaCredito> findAll() {
		return repo.findAll();
	}

}
