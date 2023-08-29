package com.devsuperior.dsmeta.utils;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class Utils {
	
	public static LocalDate dateMinusYear(LocalDate max, Long years) {
		
		  return max.minusYears(years);
		  
	}
	
	public static LocalDate currentDate() {
		return LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
	}
	
	public static LocalDate convStringToLocalDate(String date) {
		
		  DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-d");
		  return LocalDate.parse(date, formatter);
		  
	}

}
