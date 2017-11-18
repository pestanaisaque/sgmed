/**
 * 
 */
package br.umc.sgmed.utils;

import java.util.Calendar;
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
}
