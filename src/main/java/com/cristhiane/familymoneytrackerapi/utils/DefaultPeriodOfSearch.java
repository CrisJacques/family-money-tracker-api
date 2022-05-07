package com.cristhiane.familymoneytrackerapi.utils;

import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;

public abstract class DefaultPeriodOfSearch {

	public static LocalDate setStartOfPeriod() {
		LocalDate today = LocalDate.now();
		Month month = today.getMonth();
		int year = today.getYear();

		System.out.println(LocalDate.of(year, month, 1));
		
		return LocalDate.of(year, month, 1); // Início do período será o primeiro dia do mês atual
	}
	
	public static LocalDate setEndOfPeriod() {
		LocalDate today = LocalDate.now();
		Month month = today.getMonth();
		int year = today.getYear();
		
		YearMonth thisYearMonth = YearMonth.of(year, month);

		System.out.println(thisYearMonth.atEndOfMonth());
		
		return thisYearMonth.atEndOfMonth(); // Final do período será o último dia do mês atual
		
	}
}
