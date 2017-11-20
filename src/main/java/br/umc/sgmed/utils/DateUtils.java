/**
 * 
 */
package br.umc.sgmed.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @author Isaque Pestana
 *
 */
public class DateUtils {
	public static java.sql.Date configDate(java.sql.Date dataSql, Integer valueInHours) {

		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(dataSql);
		gc.add(Calendar.HOUR, valueInHours);

		return new java.sql.Date(gc.getTimeInMillis());
	}

	public static java.sql.Date getDataAtual() {
		return convertJavaDateToSqlDate(new Date());
	}

	public static java.sql.Date convertJavaDateToSqlDate(java.util.Date javaDate) {

		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(javaDate);

		return new java.sql.Date(gc.getTimeInMillis());
	}
}
