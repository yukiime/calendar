package team.item.schedule;

/**
 * 节气类
 */
public class SolarTerm extends CommemorationDay {
  int year; // 年份

  /**
   * @param year    年
   * @param month   月
   * @param day     日
   * @param content 节日名称
   */
  public SolarTerm(int year, int month, int day, String content) {
    this.year = year;
    this.month = month;
    this.day = day;
    this.content = content;
  }

  public int getYear() {
    return year;
  }
}
