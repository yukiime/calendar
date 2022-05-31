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

  public static long get0clockTimeStamp(int year, int month, int day) {
    Calendar date = Calendar.getInstance();
    date.set(year, month - 1, day);
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
}
