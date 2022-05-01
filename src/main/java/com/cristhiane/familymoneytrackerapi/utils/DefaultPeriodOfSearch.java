package com.cristhiane.familymoneytrackerapi.utils;

import java.util.Calendar;
import java.util.Date;

public abstract class DefaultPeriodOfSearch {

	public static Date setStartOfPeriod() {
		Date startDate = null;
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date()); // Configurando a data atual no calendar

		int month = calendar.get(Calendar.MONTH);
		int year = calendar.get(Calendar.YEAR);
		
		int startDay = 1; // primeiro dia do período será o primeiro dia do mês

		// Data inicial do período de busca
		calendar.set(year, month, startDay, 0, 0, 0); // Início do período será o primeiro dia do mês atual, à meia-noite
		startDate = calendar.getTime();
		System.out.println(startDate);
		
		return startDate;
	}
	
	public static Date setEndOfPeriod() {
		Date endDate = null;
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date()); // Configurando a data atual no calendar

		int month = calendar.get(Calendar.MONTH);
		int year = calendar.get(Calendar.YEAR);
		
		int endDay = calendar.getActualMaximum(Calendar.DATE); // último dia do período será o último dia do mês atual
		
		// Data final do período de busca
		calendar.set(year, month, endDay, 23, 59, 59);// Final do período será o último dia do mês atual, às 23:59:59
		endDate = calendar.getTime(); 
		System.out.println(endDate);
		
		return endDate;
		
	}
}
