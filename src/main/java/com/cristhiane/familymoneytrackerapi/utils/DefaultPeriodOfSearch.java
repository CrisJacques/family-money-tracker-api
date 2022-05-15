package com.cristhiane.familymoneytrackerapi.utils;

import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;

/**
 * Classe para construir o período padrão de busca de informações da aplicação,
 * que é o mês atual
 *
 */
public abstract class DefaultPeriodOfSearch {

	/**
	 * Configura o início do período padrão de busca como sendo o começo do mês
	 * atual
	 * 
	 * @return Primeiro dia do mês atual
	 */
	public static LocalDate setStartOfPeriod() {
		LocalDate today = LocalDate.now();
		Month month = today.getMonth();
		int year = today.getYear();

		return LocalDate.of(year, month, 1); // Início do período será o primeiro dia do mês atual
	}

	/**
	 * Configura o final do período padrão de busca como sendo o final do mês atual
	 * 
	 * @return Último dia do mês atual
	 */
	public static LocalDate setEndOfPeriod() {
		LocalDate today = LocalDate.now();
		Month month = today.getMonth();
		int year = today.getYear();

		YearMonth thisYearMonth = YearMonth.of(year, month);

		return thisYearMonth.atEndOfMonth(); // Final do período será o último dia do mês atual

	}
}
