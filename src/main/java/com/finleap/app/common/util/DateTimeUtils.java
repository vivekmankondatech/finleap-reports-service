/**
 * 
 */
package com.finleap.app.common.util;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;

/**
 * Any License information can go here
 */

/**
 * 
 * com.hiketrail.app.common.utils | hiketrail-app
 * -------------------------------------------------------------------------
 * 
 *
 * @author Vivek Mankonda
 * @version 1.0
 * @since 9 Mar 2022
 */

/**
 * <Code modification record>
 * 
 * <pre>
 * No.	Modified by (ID) 				Date (MM DD, YYYY) 	[BUG-ID] 	Description
 * ----------------------------------------------------------------------------------
 * 1	vivekmankonda.work@gmail.com		9 Mar 2022						Initial commit
 * 
 * </pre>
 */
public class DateTimeUtils {
	private DateTimeUtils() {
		throw new IllegalStateException("Utility class");
	}
	
	/**
	 * For example: 2018-12-28
	 */
	public static final String DATE = "yyyy-MM-dd";
	/**
	 * For example: 2018-12-28 10:00:00
	 */
	public static final String DATE_TIME = "yyyy-MM-dd HH:mm:ss";
	/**
	 * Example: 10:00:00
	 */
	public static final String TIME = "HHmmss";
	/**
	 * Example: 10:00
	 */
	public static final String TIME_WITHOUT_SECONDS = "HH:mm";

	/**
	 * For example: 2018-12-28 10:00
	 */
	public static final String DATE_TIME_WITHOUT_SECONDS = "yyyy-MM-dd HH:mm";

	/**
	 * Get the year
	 *
	 * @return year
	 */
	public static int getYear() {
		var localTime = LocalTime.now();
		return localTime.get(ChronoField.YEAR);
	}

	/**
	 * Get the month
	 *
	 * @return month
	 */
	public static int getMonth() {
		var localTime = LocalTime.now();
		return localTime.get(ChronoField.MONTH_OF_YEAR);
	}

	/**
	 * Get the first few days of a month
	 *
	 * @return number
	 */
	public static int getMonthOfDay() {
		var localTime = LocalTime.now();
		return localTime.get(ChronoField.DAY_OF_MONTH);
	}

	/**
	 * Formatted date as a string
	 *
	 * @param date    date
	 * @param pattern format
	 * @return date string
	 */
	public static String format(Date date, String pattern) {
		var instant = date.toInstant();
		var localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
		return localDateTime.format(DateTimeFormatter.ofPattern(pattern));
	}

	/**
	 * Parse string date as Date
	 *
	 * @param dateStr date string
	 * @param pattern format
	 * @return Date
	 */
	public static Date parse(String dateStr, String pattern) {
		var localDateTime = LocalDateTime.parse(dateStr, DateTimeFormatter.ofPattern(pattern));
		var instant = localDateTime.atZone(ZoneId.systemDefault()).toInstant();
		return Date.from(instant);
	}

	/**
	 * Increase the minute for Date, reduce the negative number
	 *
	 * @param date        Date
	 * @param plusMinutes The number of minutes to increase
	 * @return new date
	 */
	public static Date addMinutes(Date date, Long plusMinutes) {
		var dateTime = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
		LocalDateTime newDateTime = dateTime.plusMinutes(plusMinutes);
		return Date.from(newDateTime.atZone(ZoneId.systemDefault()).toInstant());
	}

	/**
	 * increase time
	 *
	 * @param date date
	 * @param hour The number of hours to increase
	 * @return new date
	 */
	public static Date addHour(Date date, Long hour) {
		var dateTime = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
		var localDateTime = dateTime.plusHours(hour);
		return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
	}

	/**
	 * @return returns the start time of the day
	 */
	public static Date getStartTime() {
		LocalDateTime now = LocalDateTime.now().withHour(0).withMinute(0).withSecond(0);
		return localDateTime2Date(now);
	}

	/**
	 * @return returns the end time of the day
	 */
	public static Date getEndTime() {
		LocalDateTime now = LocalDateTime.now().withHour(23).withMinute(59).withSecond(59).withNano(999);
		return localDateTime2Date(now);
	}

	/**
	 * month reduction
	 *
	 * @param monthsToSubtract month
	 * @return Date
	 */
	public static Date minusMonths(long monthsToSubtract) {
		var localDate = LocalDate.now().minusMonths(monthsToSubtract);
		return localDate2Date(localDate);
	}

	/**
	 * Convert local date to date
	 * 
	 * @param localDate
	 * @return
	 */
	public static Date localDate2Date(LocalDate localDate) {
		var zonedDateTime = localDate.atStartOfDay(ZoneId.systemDefault());
		return Date.from(zonedDateTime.toInstant());
	}

	/**
	 * Convert local date time to date
	 * 
	 * @param localDateTime
	 * @return
	 */
	public static Date localDateTime2Date(LocalDateTime localDateTime) {
		return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
	}
	
	/**
	 * Add Days to Given Date
	 * 
	 * @param selectedDate
	 * @param daysToAdd
	 * @return
	 */
	public static LocalDate addDays(LocalDate selectedDate, int daysToAdd) {
		return selectedDate.plusDays(daysToAdd);
	}
	
	/**
	 * Get the start of week date from the current date
	 * 
	 * ex: getWeekStartDate(DayOfWeek.MONDAY)
	 * 
	 * @param startDayOfWeek
	 * @return
	 */
	public static LocalDate getWeekStartDate(final DayOfWeek startDayOfWeek) {
		return getWeekStartDateFromDate(LocalDate.now(), startDayOfWeek);
	}
	
	/**
	 * Get the start of week date from the specified date
	 * 
	 * ex: getWeekStartDateOfDate(LocalDate.now(), DayOfWeek.MONDAY)
	 * 
	 * @param today
	 * @param startDayOfWeek
	 * @return
	 */
	public static LocalDate getWeekStartDateFromDate(LocalDate today, final DayOfWeek startDayOfWeek) {
		return today.with(TemporalAdjusters.previousOrSame(startDayOfWeek));
	}

	/**
	 * Get the end of the week date from the current date
	 * 
	 * ex: getWeekStartDate(DayOfWeek.SUNDAY)
	 * 
	 * @param endDayOfWeek
	 * @return
	 */
	public static LocalDate getWeekEndDate(final DayOfWeek endDayOfWeek) {
		return getWeekEndDateFromDate(LocalDate.now(), endDayOfWeek);
	}

	/**
	 * Get the end of the week date from the current date
	 * 
	 * ex: getWeekEndDateOfDate(LocalDate.now(), DayOfWeek.SUNDAY)
	 * 
	 * @param today
	 * @param endDayOfWeek
	 * @return
	 */
	public static LocalDate getWeekEndDateFromDate(LocalDate today, final DayOfWeek endDayOfWeek) {
		return today.with(TemporalAdjusters.nextOrSame(endDayOfWeek));
	}

	/**
	 * Get the start of the month date from current date
	 * 
	 * @return
	 */
	public static LocalDate getMonthStartDate() {
		return getMonthStartDateFromDate(LocalDate.now());
	}

	/**
	 * Get the start of the month date from specified date
	 * 
	 * @param today
	 * @return
	 */
	public static LocalDate getMonthStartDateFromDate(LocalDate today) {
		return today.withDayOfMonth(1);
	}

	/**
	 * Get the end of the month date from current date
	 * 
	 * @return
	 */
	public static LocalDate getMonthEndDate() {
		return getMonthEndDateFromDate(LocalDate.now());
	}

	/**
	 * Get the end of the month date from specified date
	 * 
	 * @param today
	 * @return
	 */
	public static LocalDate getMonthEndDateFromDate(LocalDate today) {
		return today.withDayOfMonth(today.lengthOfMonth());
	}
}
