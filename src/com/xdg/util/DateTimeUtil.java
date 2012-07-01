package com.xdg.util;
import java.text.DateFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.joda.time.DateMidnight;

public final class DateTimeUtil {

	public static String convertDateText(String dateString, String previousformat, String returnformat) {
		try {
			Date date = parseDate(dateString, previousformat);
			// Format the current time.
			return new SimpleDateFormat(returnformat).format(date);
		} catch (Exception e) {
			e.printStackTrace();
			return dateString;
		}
	}

	public static Date parseDate(String dateString, String pattern) {
		SimpleDateFormat formatter = new SimpleDateFormat(pattern);
		// Parse the previous string back into a Date.
		ParsePosition pos = new ParsePosition(0);
		return formatter.parse(dateString, pos);
	}

	public static String getDayOfMonth(Date sDate) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd");
		String str = sdf.format(sDate);
		return str;
	}

	public static String getMonth(Date sDate) {
		SimpleDateFormat sdf = new SimpleDateFormat("MM");
		String str = sdf.format(sDate);
		return str;
	}

	public static String getCurrentDate() {
		Date now = new Date();
		DateFormat df = DateFormat.getDateInstance(DateFormat.FULL, Locale.JAPAN);
		String str = df.format(now);
		return str;

	}

	public static String getCurrentTime() {
		Date now = new Date();
		DateFormat df = DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.FULL, Locale.JAPAN);
		String str = df.format(now);
		return str;
	}

	public static String getCurrentDateLong() {
		Date now = new Date();
		DateFormat df = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG);
		String str = df.format(now);
		return str;
	}

	public static String getCurrentDayOfWeek() {
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("E");
		String str = sdf.format(now);
		return str;
	}

	public static String getCurrentYear() {
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		String str = sdf.format(now);
		return str;
	}

	public static String format(Date date, String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(date);
	}

	public static String getCurrentDateFormattedAs(String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(new Date());
	}

	public static Date getAddDate(int cal) {
		Calendar day = Calendar.getInstance();
		day.add(Calendar.DATE, cal);
		return day.getTime();
	}

	public static int diffByYear(Date from, Date to) {
		return diffByYear(from.getTime(), to.getTime());
	}

	public static int diffByYear(long from, long to) {
		Calendar calendarA = Calendar.getInstance();
		Calendar calendarB = Calendar.getInstance();
		int multiplier;
		if (from - to > 0) {
			multiplier = -1;
			calendarA.setTimeInMillis(from);
			calendarB.setTimeInMillis(to);
		} else {
			multiplier = 1;
			calendarA.setTimeInMillis(to);
			calendarB.setTimeInMillis(from);
		}

		int years = calendarA.get(Calendar.YEAR) - calendarB.get(Calendar.YEAR);
		int months = calendarA.get(Calendar.MONTH) - calendarB.get(Calendar.MONTH);
		int days = calendarA.get(Calendar.DAY_OF_MONTH) - calendarB.get(Calendar.DAY_OF_MONTH);
		if (years > 0 && (months < 0 || (months == 0 && days < 0))) {
			years -= 1;
		}
		return years * multiplier;
	}

    public static double diffByDay(long from ,long to){
        return (to-from)/(24*3600*1000);
    }

    public static double diffByDay(Date from,Date to){
        return diffByDay(from.getTime(),to.getTime());
    }

	public static boolean isSameDateWithCurrentAndRegdate(Date registDate) {
		DateMidnight  midnightRegDate = new DateMidnight(registDate);
		DateMidnight currentDate = new DateMidnight(System.currentTimeMillis());
		return midnightRegDate.equals(currentDate);
	}

    /**
     * format the date with  like 2012-10-18 12:36:48
     * @param date
     * @return
     */
    public static String formatYmdHms(Date date){
        return new SimpleDateFormat("yyyy-MM-dd kk:mm:ss").format(date);
    }

    public static String formatYmd(Date date){
        return new SimpleDateFormat("yyyy-MM-dd").format(date);
    }

}