package com.xdg.util;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtil {
    public static int getYear(Date date) {
        return convertCalendar(date).get(Calendar.YEAR);
    }

    public static int getDay(Date date){
       return convertCalendar(date).get(Calendar.DAY_OF_MONTH);

    }

    public static Calendar convertCalendar(Date date) {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        return calendar;
    }
}
