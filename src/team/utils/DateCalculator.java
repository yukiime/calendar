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
}
