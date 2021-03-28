package com.bishwa.project.Utils;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Author: Bishwa
 * Date: 30/01/2021
 * Time: 18:47
 */
public class DateUtils {
    public static Date getNextSchedulingTime(int dayOffset, int hr, int min, int s) {
        Calendar nextWorkingDay = handleOffDay(new GregorianCalendar());

        Calendar nextScheduledDate  = new GregorianCalendar(
            nextWorkingDay.get(Calendar.YEAR),
            nextWorkingDay.get(Calendar.MONTH),
            nextWorkingDay.get(Calendar.DATE),
            hr,
            min,
            s
        );

        if(nextWorkingDay.before(nextScheduledDate)) return nextScheduledDate.getTime();

        nextScheduledDate.add(Calendar.DATE, dayOffset);

        return handleOffDay(nextScheduledDate).getTime();
    }

    private static Calendar handleOffDay(Calendar calendar) {
        if(calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
            calendar.add(Calendar.DATE, 1);
            return calendar;
        }

        return calendar;
    }
}
