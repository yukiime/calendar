package team.utils;

import java.util.Calendar;

public class DateCalculator {
    /**
     * 用于计算上个月的天数
     *
     * @param date 本月的 Calendar 对象
     */
    public static int dayOfLastMonth(Calendar date) {
        int year = date.get(Calendar.MONTH) != 0 ? date.get(Calendar.YEAR) : date.get(Calendar.YEAR) - 1;
        int month = date.get(Calendar.MONTH) != 0 ? date.get(Calendar.MONDAY) - 1 : 12;
        Calendar lastMonthDate = Calendar.getInstance();
        lastMonthDate.set(year, month, 1);
        return lastMonthDate.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    public static int dayOfWeek(int year, int month, int solarDate) {
        Calendar date = Calendar.getInstance();
        date.set(year, month - 1, solarDate);
        return date.get(Calendar.DAY_OF_WEEK) - 1;
    }

    public static int distanceOfToday(int year, int month, int solarDate) {
        Calendar today = Calendar.getInstance();
        return (int) ((get0clockTimeStamp(year, month, solarDate)
                - get0clockTimeStamp(today.get(Calendar.YEAR), today.get(Calendar.MONTH) + 1,
                        today.get(Calendar.DATE)))
                / 86400000L);

    }

    public static long get0clockTimeStamp(int year, int month, int solarDate) {
        Calendar date = Calendar.getInstance();
        date.set(year, month - 1, solarDate);
        date.set(Calendar.SECOND, 0);
        date.set(Calendar.MINUTE, 0);
        date.set(Calendar.HOUR_OF_DAY, 0);
        date.set(Calendar.MILLISECOND, 0);
        return date.getTimeInMillis();
    }

    public static Calendar getCalendarInstance(int year, int month, int solarDate) {
        Calendar date = Calendar.getInstance();
        date.set(year, month - 1, solarDate);
        return date;
    }

    public static boolean checkQueryVaild(int year, int month, int day, int type) {
        if (year < 1901 || year > 2100 || month < 1 || month > 12)
            return false;
        if (type == 1) {
            if (day < 1 || day > 31)
                return false;
            if (month == 2)
                if ((year % 4 == 0) && (year % 100 == 0) && (day > 29)) {
                    return false;
                } else if (day > 28)
                    return false;
            if ((month == 4 || month == 6 || month == 9 || month == 11) && (day == 31)) {
                return false;
            }
        } else if (type == 2)
            if (day > 30)
                return false;
        return true;
    }
}
