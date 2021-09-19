package com.carscan.utility;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtils {
	public static DateTimeFormatter yyyyMMdd = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	
	private static String dateFormat="yyyy-MM-dd";

	public static String convertDateToString(LocalDate localDate) {

		return convertDateToString(localDate, yyyyMMdd);
	}

	public static String convertDateToString(LocalDate inputDate, DateTimeFormatter formatter) {

		return inputDate.format(formatter);
	}
	
	public static LocalDate fromString(String date) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormat);
		return LocalDate.parse(date, formatter);
	}

}