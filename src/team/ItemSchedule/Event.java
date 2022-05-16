package team.ItemSchedule;

import java.util.Calendar;

public class Event {
  /**
   * 返回星期几对应的数字
   * 
   * @param date 一个日历对象
   * @return 星期几对应的数字，星期天 -> 7
   */
  public static int queryDayOfWeek(Calendar date) {
    int tmp = (date.get(Calendar.DAY_OF_WEEK) + 6) % 7;
    return tmp == 0 ? 7 : tmp;
  }

  /**
   * [测试]输出某月日期格式
   * 
   * @param date 一个日历对象
   */
  public static void printWeekInfo(Calendar date) {
    int firstDay = date.get(Calendar.DAY_OF_WEEK);
    for (int i = 1; i <= date.getActualMaximum(Calendar.DAY_OF_MONTH) + firstDay - 1; i++) {
      if (firstDay > i)
        System.out.print("  ");
      else
        System.out.print(i - firstDay + 1 + " ");
      if (i % 7 == 0)
        System.out.print("\n");
    }
  }

  public static void main(String[] args) {
    Calendar date = Calendar.getInstance();
    date.set(2022, 3, 1);
    printWeekInfo(date);
  }
}
